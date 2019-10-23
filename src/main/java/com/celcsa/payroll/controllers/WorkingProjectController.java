package com.celcsa.payroll.controllers;

import com.celcsa.payroll.converters.WorkingProjectDTOToWP;
import com.celcsa.payroll.converters.WorkingProjectToDTO;
import com.celcsa.payroll.dtos.WorkingProjectDTO;
import com.celcsa.payroll.services.WorkingProjectServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import reactor.core.publisher.Mono;

/**
 * WorkingProjectController
 */
@RestController
@Api(value = "Celcsa Projects Controller", description = "Celcsa Projects Related Endpoints", tags = "Celcsa Project Controller")
public class WorkingProjectController {

    public WorkingProjectServiceImpl workingProjectServiceImpl;
    public WorkingProjectToDTO workingProjectToDTO;
    public WorkingProjectDTOToWP workingProjectDTOToWP;

    @Autowired
    public WorkingProjectController(WorkingProjectServiceImpl workingProjectServiceImpl,
            WorkingProjectToDTO workingProjectToDTO,
            WorkingProjectDTOToWP workingProjectDTOToWP) {
        this.workingProjectServiceImpl = workingProjectServiceImpl;
        this.workingProjectToDTO = workingProjectToDTO;
        this.workingProjectDTOToWP = workingProjectDTOToWP;
    }

    @PostMapping("project")
    public Mono<WorkingProjectDTO> addProject(
            @ModelAttribute WorkingProjectDTO workingProjectDTO, BindingResult bindingResults) {
        return workingProjectServiceImpl.saveProject(
            workingProjectDTOToWP.convert(workingProjectDTO)).map(workingProjectToDTO::convert);
    
    }

    @GetMapping("/project/{name}")
    public Mono<WorkingProjectDTO> getByProjectName(@PathVariable(required = true) String name){
        return workingProjectServiceImpl.getByProjectName(name).map(workingProjectToDTO::convert);
    }

}