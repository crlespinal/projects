package com.celcsa.payroll.converters;

import com.celcsa.payroll.domain.employee.projects.WorkingProject;
import com.celcsa.payroll.dtos.WorkingProjectDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.Synchronized;

/**
 * WorkingProjectToDTO
 */
@Component
public class WorkingProjectToDTO implements Converter<WorkingProject, WorkingProjectDTO>{

    @Synchronized
    @Override
    public WorkingProjectDTO convert(WorkingProject source) {
        return WorkingProjectDTO.builder()
        .projectName(source.getProjectName())
        .projectDuration(source.getProjectDuration())
        .estimatedDuration(source.getEstimatedProjectDuration())
        .workingProjectStatus(source.getWorkingProjectStatus()).build();
    }

    
}