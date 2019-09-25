package com.celcsa.payroll.controllers;

import com.celcsa.payroll.converters.CelcsaEmployeeDTOToEmployee;
import com.celcsa.payroll.converters.CelcsaEmployeeToDTO;
import com.celcsa.payroll.converters.WorkingProjectDTOToWP;
import com.celcsa.payroll.converters.WorkingProjectToDTO;
import com.celcsa.payroll.domain.employee.CelcsaEmployee;
import com.celcsa.payroll.dtos.CelcsaEmployeeDTO;
import com.celcsa.payroll.dtos.WorkingProjectDTO;
import com.celcsa.payroll.services.CelcsaEmployeeServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * EmployeeController
 */
@RestController
public class EmployeeController {

    public CelcsaEmployeeServiceImpl celcsaEmployeeService;
    public CelcsaEmployeeToDTO celcsaEmployeeToDTO;
    public CelcsaEmployeeDTOToEmployee celcsaEmployeeDTOToEmployee;
    public WorkingProjectToDTO workingProjectToDTO;
    public WorkingProjectDTOToWP workingProjectDTOToWP;

    public EmployeeController(CelcsaEmployeeServiceImpl celcsaEmployeeService, CelcsaEmployeeToDTO celcsaEmployeeToDTO,
            CelcsaEmployeeDTOToEmployee celcsaEmployeeDTOToEmployee, WorkingProjectToDTO workingProjectToDTO,
            WorkingProjectDTOToWP workingProjectDTOToWP) {
        this.celcsaEmployeeService = celcsaEmployeeService;
        this.celcsaEmployeeToDTO = celcsaEmployeeToDTO;
        this.celcsaEmployeeDTOToEmployee = celcsaEmployeeDTOToEmployee;
        this.workingProjectToDTO = workingProjectToDTO;
        this.workingProjectDTOToWP = workingProjectDTOToWP;
    }

    @GetMapping("/user/id/{id}")
    public Mono<CelcsaEmployeeDTO> getById(@PathVariable String id) {
        return celcsaEmployeeService.findById(id).map(celcsaEmployeeToDTO::convert);
    }

    @GetMapping("/user/{username}")
    public Mono<CelcsaEmployeeDTO> getByUserName(@PathVariable String username) {
        return celcsaEmployeeService.findByUsernameLike(username).map(celcsaEmployeeToDTO::convert);
    }

    @PostMapping("employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CelcsaEmployeeDTO> newEmployee(@ModelAttribute CelcsaEmployeeDTO dto, BindingResult bindingResults) {
        return celcsaEmployeeService.saveEmployee(celcsaEmployeeDTOToEmployee.convert(dto))
                .map(celcsaEmployeeToDTO::convert);
    }

    @PostMapping("/update/employee")
    public Mono<CelcsaEmployeeDTO> updateEmployee(@ModelAttribute CelcsaEmployeeDTO dto, BindingResult bindingResults) {
        return celcsaEmployeeService.updateEmployee(celcsaEmployeeDTOToEmployee.convert(dto))
                .map(celcsaEmployeeToDTO::convert);
    }

    @PostMapping("/employee/project")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CelcsaEmployeeDTO> addWorkingProjectToEmployee(@ModelAttribute WorkingProjectDTO workingProjectDTO,
            BindingResult bindingResults) {
        if (!workingProjectDTO.isValid())
            throw new IllegalArgumentException("Working Project DTO invalid");
        return celcsaEmployeeService.addWorkingProjectToEmployee(workingProjectDTO.getId(),
                workingProjectDTOToWP.convert(workingProjectDTO)).map(celcsaEmployeeToDTO::convert);
    }

    @GetMapping("/employee/{id}/projects")
    public Flux<WorkingProjectDTO> getEmployeeProjects(@PathVariable String id){
        return celcsaEmployeeService.getEmployeeProjects(id).map(workingProjectToDTO::convert);
    }

}