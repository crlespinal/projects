package com.celcsa.payroll.converters;

import com.celcsa.payroll.domain.employee.projects.WorkingProject;
import com.celcsa.payroll.dtos.WorkingProjectDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
        .projectName(StringUtils.capitalize(source.getProjectName()))
        .projectDuration(source.getProjectDuration())
        .estimatedDuration(source.getEstimatedProjectDuration())
        .workingProjectStatus(source.getWorkingProjectStatus())
        .shifts(source.getShifts())
        .build();
    }

    
}