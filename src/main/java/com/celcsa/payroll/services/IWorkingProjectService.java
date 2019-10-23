package com.celcsa.payroll.services;

import com.celcsa.payroll.domain.employee.projects.WorkingProject;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * IWorkingProjectService
 */
public interface IWorkingProjectService {

    public Mono<WorkingProject> saveProject(WorkingProject workingProject);

    public Mono<WorkingProject> getByProjectName(String projectName);

    public Mono<WorkingProject> updateProject(WorkingProject workingProject);

    public Flux<WorkingProject> getByEmployeeId(String employeeId);
    
}