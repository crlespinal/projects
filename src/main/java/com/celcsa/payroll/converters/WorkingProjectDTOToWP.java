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
public class WorkingProjectDTOToWP implements Converter<WorkingProjectDTO, WorkingProject> {

    @Synchronized
    @Override
    public WorkingProject convert(WorkingProjectDTO source) {
        return WorkingProject.builder()
        .projectName(source.getProjectName())
        .projectDuration(source.getProjectDuration())
        .estimatedProjectDuration(source.getEstimatedDuration()).build();
    }

    
}