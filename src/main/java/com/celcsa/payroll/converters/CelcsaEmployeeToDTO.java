package com.celcsa.payroll.converters;

import com.celcsa.payroll.domain.employee.CelcsaEmployee;
import com.celcsa.payroll.dtos.CelcsaEmployeeDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

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
        .firstName(source.getFirstName())
        .lastName(source.getLastName())
        .middleName(source.getMiddleName())
        .username(source.getUsername()).build();
    }

    
    
}