package com.celcsa.payroll.repositories;

import com.celcsa.payroll.domain.employee.projects.WorkingProject;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

/**
 * WorkingProjectRepository
 */
@Repository
public interface WorkingProjectRepository extends ReactiveMongoRepository<WorkingProject, String>{

    public Mono<WorkingProject> findByProjectName(String projectName);    
    
}