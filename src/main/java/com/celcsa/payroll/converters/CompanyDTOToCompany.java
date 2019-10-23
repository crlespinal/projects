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
public class CompanyDTOToCompany implements Converter<CompanyDTO, Company>{

    @Synchronized
    @Override
    public Company convert(CompanyDTO source) {
        return Company.builder()
        .address(source.getAddress())
        .alternateAddresses(source.getAlternateAddresses())
        .alternateCompanyNames(source.getAlternateCompanyNames())
        .city(source.getCity())
        .state(source.getState())
        .zipCode(source.getZipCode())
        .build();
    }

    
    
}