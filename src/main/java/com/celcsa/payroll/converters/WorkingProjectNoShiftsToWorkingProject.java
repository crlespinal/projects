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
public class WorkingProjectNoShiftsToWorkingProject implements Converter<WorkingProject, WorkingProjectNoShiftsDTO> {

    @Synchronized
    @Override
    public WorkingProjectNoShiftsDTO convert(WorkingProject source) {
        return WorkingProjectNoShiftsDTO.builder()
        .projectName(source.getProjectName().toLowerCase())
        .estimatedDuration(source.getEstimatedProjectDuration())
        .projectDuration(source.getProjectDuration()).build();
    }

    
    
}