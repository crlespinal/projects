package com.celcsa.payroll.converters;

import com.celcsa.payroll.domain.Company;
import com.celcsa.payroll.dtos.CompanyDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.Synchronized;

/**
 * CelcsaEmployeeToDTO
 */
@Component
public class CompanyToDTO implements Converter<Company, CompanyDTO>{

    @Synchronized
    @Override
    public CompanyDTO convert(Company source) {
        return CompanyDTO.builder()
        .address(source.getAddress())
        .city(source.getCity())
        .state(source.getState())
        .zipCode(source.getZipCode())
        .alternateAddresses(source.getAlternateAddresses())
        .alternateCompanyNames(source.getAlternateCompanyNames())
        .build();
    }

    
    
}