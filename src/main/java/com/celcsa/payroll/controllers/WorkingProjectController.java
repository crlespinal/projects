package com.celcsa.payroll.controllers;

import com.celcsa.payroll.converters.WorkingProjectNoShiftsDTOToWorkingProject;
import com.celcsa.payroll.converters.WorkingProjectNoShiftsToWorkingProject;
import com.celcsa.payroll.domain.employee.CelcsaEmployee;
import com.celcsa.payroll.dtos.WorkingProjectNoShiftsDTO;
import com.celcsa.payroll.services.WorkingProjectServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * WorkingProjectController
 */
@RestController
public class WorkingProjectController {

    public WorkingProjectServiceImpl workingProjectServiceImpl;

    public WorkingProjectNoShiftsDTOToWorkingProject workingProjectNoShiftsDTOToWorkingProject;

    public WorkingProjectNoShiftsToWorkingProject workingProjectNoShiftsToWorkingProject;

    @Autowired
    public WorkingProjectController(WorkingProjectServiceImpl workingProjectServiceImpl,
            WorkingProjectNoShiftsDTOToWorkingProject workingProjectNoShiftsDTOToWorkingProject
            ,WorkingProjectNoShiftsToWorkingProject workingProjectNoShiftsToWorkingProject) {
        this.workingProjectServiceImpl = workingProjectServiceImpl;
        this.workingProjectNoShiftsDTOToWorkingProject = workingProjectNoShiftsDTOToWorkingProject;
        this.workingProjectNoShiftsToWorkingProject = workingProjectNoShiftsToWorkingProject;
    }

    @PostMapping("project")
    public Mono<WorkingProjectNoShiftsDTO> addProjectToEmployee(
            @ModelAttribute WorkingProjectNoShiftsDTO workingProjectNoShiftsDTO, BindingResult bindingResults) {
        return workingProjectServiceImpl.saveProject(
                workingProjectNoShiftsDTOToWorkingProject.convert(workingProjectNoShiftsDTO)).map(workingProjectNoShiftsToWorkingProject::convert);
    
    }

}