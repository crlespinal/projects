package com.celcsa.payroll.services;

import java.util.Iterator;
import java.util.List;

import com.celcsa.payroll.domain.employee.CelcsaEmployee;
import com.celcsa.payroll.domain.employee.projects.WorkingProject;
import com.celcsa.payroll.exceptions.EmployeeProjectExistException;
import com.celcsa.payroll.repositories.CelcsaEmployeeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * CelcsaEmployeeService
 */
@Service
@Slf4j
public class CelcsaEmployeeServiceImpl implements ICelcsaEmployeeService {

    public CelcsaEmployeeRepo repo;

    public ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public CelcsaEmployeeServiceImpl(CelcsaEmployeeRepo repo, ReactiveMongoTemplate mongoTemplate) {
        this.repo = repo;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<CelcsaEmployee> findByUsernameLike(String username) {
        return repo.findByUsernameLike(username);
    }

    @Override
    public Mono<CelcsaEmployee> saveEmployee(CelcsaEmployee employee) {
        return repo.save(employee);
    }

    @Override
    public Mono<CelcsaEmployee> updateEmployee(CelcsaEmployee employee) {
        return repo.findByUsernameLike(employee.getUsername()).flatMap(item -> {
            return repo.save(item);
        });
    }

    @Transactional
    @Override
    public Mono<CelcsaEmployee> addWorkingProjectToEmployee(String username, String projectName) {
        return mongoTemplate.find(Query.query(Criteria.where("username").is(username).and("workingProjects").is(projectName)), CelcsaEmployee.class).collectList()
                .flatMap(item -> {
                    log.debug("****** Item Size: " + item.size());
                    if(!item.isEmpty())
                        throw new EmployeeProjectExistException();
                    return repo.findByUsernameLike(username).flatMap(emp ->{
                        emp.addWorkingProject(projectName);
                        return repo.save(emp);
                    });
        }).defaultIfEmpty(new CelcsaEmployee());
    }

    @Override
    public Mono<CelcsaEmployee> findById(String id) {
        return repo.findById(id);
    }
    
}