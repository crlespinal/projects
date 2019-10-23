package com.celcsa.payroll.converters;

import com.celcsa.payroll.domain.schedule.Shift;
import com.celcsa.payroll.dtos.ShiftDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.Synchronized;

/**
 * WorkingProjectToDTO
 */
@Component
public class ShiftToDTO implements Converter<Shift, ShiftDTO> {

    @Synchronized
    @Override
    public ShiftDTO convert(Shift source) {
        return ShiftDTO.builder()
        .employeeId(source.getEmployeeId())
        .from(source.getFrom())
        .to(source.getTo())
        .projectId(StringUtils.capitalize(source.getProjectId()))
        .build();
    }

    
}