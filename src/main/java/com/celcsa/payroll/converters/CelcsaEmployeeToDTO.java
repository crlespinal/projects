package com.celcsa.payroll.converters;

import com.celcsa.payroll.domain.employee.CelcsaEmployee;
import com.celcsa.payroll.dtos.CelcsaEmployeeDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.Synchronized;

/**
 * CelcsaEmployeeToDTO
 */
@Component
public class CelcsaEmployeeToDTO implements Converter<CelcsaEmployee, CelcsaEmployeeDTO>{

    @Synchronized
    @Override
    public CelcsaEmployeeDTO convert(CelcsaEmployee source) {
        return CelcsaEmployeeDTO.builder()
        .id(source.getId())
        .firstName(StringUtils.capitalize(source.getFirstName()))
        .lastName(StringUtils.capitalize(source.getLastName()))
        .middleName(StringUtils.capitalize(source.getMiddleName()))
        .username(source.getUsername().toLowerCase()).build();
    }

    
    
}