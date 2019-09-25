package com.celcsa.payroll.services;

import com.celcsa.payroll.domain.employee.CelcsaEmployee;
import com.celcsa.payroll.domain.employee.projects.WorkingProject;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ICelcsaEmployeeService
 */
public interface ICelcsaEmployeeService {

    public Mono<CelcsaEmployee> findById(String id);

    public Mono<CelcsaEmployee> findByUsernameLike(String username);

    public Mono<CelcsaEmployee> saveEmployee(CelcsaEmployee employee); 

    public Mono<CelcsaEmployee> updateEmployee(CelcsaEmployee employee); 

    public Mono<CelcsaEmployee> addWorkingProjectToEmployee(String username, WorkingProject workingProject);

    public Flux<WorkingProject> getEmployeeProjects(String id);
    
}