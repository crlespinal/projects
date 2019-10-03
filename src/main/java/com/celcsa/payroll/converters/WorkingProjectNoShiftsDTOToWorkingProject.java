package com.celcsa.payroll.converters;

import com.celcsa.payroll.domain.employee.projects.WorkingProject;
import com.celcsa.payroll.dtos.WorkingProjectNoShiftsDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.Synchronized;

/**
 * CelcsaEmployeeToDTO
 */
@Component
public class WorkingProjectNoShiftsDTOToWorkingProject implements Converter<WorkingProjectNoShiftsDTO, WorkingProject> {

    @Synchronized
    @Override
    public WorkingProject convert(WorkingProjectNoShiftsDTO source) {
        return WorkingProject.builder()
        .projectName(source.getProjectName().toLowerCase())
        .estimatedProjectDuration(source.getEstimatedDuration())
        .projectDuration(source.getProjectDuration()).build();
    }

    
    
}