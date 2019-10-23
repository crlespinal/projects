package com.celcsa.payroll.controllers;

import com.celcsa.payroll.converters.CelcsaEmployeeDTOToEmployee;
import com.celcsa.payroll.converters.CelcsaEmployeeToDTO;
import com.celcsa.payroll.converters.CelcsaEmployeeToDTOFull;
import com.celcsa.payroll.converters.WorkingProjectDTOToWP;
import com.celcsa.payroll.converters.WorkingProjectToDTO;
import com.celcsa.payroll.dtos.CelcsaEmployeeDTO;
import com.celcsa.payroll.dtos.PostWorkingProject;
import com.celcsa.payroll.dtos.WorkingProjectDTO;
import com.celcsa.payroll.services.CelcsaEmployeeServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * EmployeeController
 */
@RestController
@Api(value = "Celcsa Employee Controller", description = "Celcsa Employee Related Endpoints", tags = "Celcsa Employee Controller")
public class EmployeeController {

    public CelcsaEmployeeServiceImpl celcsaEmployeeService;
    public CelcsaEmployeeToDTO celcsaEmployeeToDTO;
    public CelcsaEmployeeDTOToEmployee celcsaEmployeeDTOToEmployee;
    public WorkingProjectToDTO workingProjectToDTO;
    public WorkingProjectDTOToWP workingProjectDTOToWP;
    public CelcsaEmployeeToDTOFull celcsaEmployeeToDTOFull;

    public EmployeeController(CelcsaEmployeeServiceImpl celcsaEmployeeService, CelcsaEmployeeToDTO celcsaEmployeeToDTO,
            CelcsaEmployeeDTOToEmployee celcsaEmployeeDTOToEmployee, WorkingProjectToDTO workingProjectToDTO,
            WorkingProjectDTOToWP workingProjectDTOToWP, CelcsaEmployeeToDTOFull celcsaEmployeeToDTOFull) {
        this.celcsaEmployeeService = celcsaEmployeeService;
        this.celcsaEmployeeToDTO = celcsaEmployeeToDTO;
        this.celcsaEmployeeDTOToEmployee = celcsaEmployeeDTOToEmployee;
        this.workingProjectToDTO = workingProjectToDTO;
        this.workingProjectDTOToWP = workingProjectDTOToWP;
        this.celcsaEmployeeToDTOFull = celcsaEmployeeToDTOFull;
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
    public Mono<CelcsaEmployeeDTO> addWorkingProjectToEmployee(@ModelAttribute PostWorkingProject postWorkingProject, BindingResult bindingResults) {
        try{
        return celcsaEmployeeService.addWorkingProjectToEmployee(postWorkingProject.getUsername(), postWorkingProject.getProjectName()).map(celcsaEmployeeToDTO::convert);
        }catch(Exception e){
            e.printStackTrace();
            return Mono.just(new CelcsaEmployeeDTO());
        }
    }

    /*@GetMapping("/employee/{id}/projects")
    public Flux<WorkingProjectDTO> getEmployeeProjects(@PathVariable String id){
        return celcsaEmployeeService.getEmployeeProjects(id).map(workingProjectToDTO::convert);
    }*/

    @GetMapping("/employee/{id}/full")
    public Mono<CelcsaEmployeeDTO> getEmployeeAndProjects(@PathVariable String id){
        return celcsaEmployeeService.findById(id).map(celcsaEmployeeToDTOFull::convert);
    }

}