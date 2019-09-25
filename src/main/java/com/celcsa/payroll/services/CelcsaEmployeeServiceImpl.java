package com.celcsa.payroll.services;

import com.celcsa.payroll.domain.employee.CelcsaEmployee;
import com.celcsa.payroll.domain.employee.projects.WorkingProject;
import com.celcsa.payroll.repositories.CelcsaEmployeeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * CelcsaEmployeeService
 */
@Service
public class CelcsaEmployeeServiceImpl implements ICelcsaEmployeeService {

    @Autowired
    CelcsaEmployeeRepo repo;

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
    public Mono<CelcsaEmployee> addWorkingProjectToEmployee(String id, WorkingProject workingProject) {
        return repo.findById(id).flatMap(item -> {
            item.addWorkingProject(workingProject);
            return repo.save(item);
        });
    }

    @Override
    public Mono<CelcsaEmployee> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public Flux<WorkingProject> getEmployeeProjects(String id) {
       return  repo.findById(id).flatMapMany(item -> Flux.fromIterable(item.getWorkingProjects()));
    }

    
}