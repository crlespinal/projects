package com.celcsa.payroll.services;

import com.celcsa.payroll.domain.employee.projects.WorkingProject;
import com.celcsa.payroll.exceptions.WorkingProjectExistException;
import com.celcsa.payroll.repositories.WorkingProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * WorkingProjectServiceImpl
 */
@Service
public class WorkingProjectServiceImpl implements IWorkingProjectService {

    public WorkingProjectRepository workingProjectRepository;
    public CelcsaEmployeeServiceImpl celcsaEmployeeServiceImpl;
    public ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public WorkingProjectServiceImpl(WorkingProjectRepository workingProjectRepository,
            CelcsaEmployeeServiceImpl celcsaEmployeeServiceImpl, ReactiveMongoTemplate mongoTemplate) {
        this.workingProjectRepository = workingProjectRepository;
        this.celcsaEmployeeServiceImpl = celcsaEmployeeServiceImpl;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<WorkingProject> saveProject(WorkingProject workingProject) {
        return getByProjectName(workingProject.getProjectName()).flatMap(project -> {
            if (project != null && project.isValid())
                throw new WorkingProjectExistException();
            return workingProjectRepository.save(workingProject);
        });
    }

    @Override
    public Mono<WorkingProject> getByProjectName(String projectName) {
        return mongoTemplate.find(Query.query(Criteria.where("projectName").is(projectName)), WorkingProject.class)
                .next().defaultIfEmpty(new WorkingProject());
    }

    @Override
    public Mono<WorkingProject> updateProject(WorkingProject workingProject) {
        return workingProjectRepository.findById(workingProject.getId()).flatMap(item -> {
            item.setEstimatedProjectDuration(workingProject.getEstimatedProjectDuration());
            item.setProjectDuration(workingProject.getProjectDuration());
            return workingProjectRepository.save(item);
        });
    }

    @Override
    public Flux<WorkingProject> getByEmployeeId(String employeeId) {
        return mongoTemplate.find(Query.query(Criteria.where("employeeId").is(employeeId)).with(new Sort(Sort.Direction.ASC, "projectName")), WorkingProject.class);
    }

}