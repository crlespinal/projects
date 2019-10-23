package com.celcsa.payroll.controllers;

import com.celcsa.payroll.converters.CompanyDTOToCompany;
import com.celcsa.payroll.converters.CompanyToDTO;
import com.celcsa.payroll.dtos.CompanyDTO;
import com.celcsa.payroll.services.CompanyServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import reactor.core.publisher.Mono;

/**
 * CompanyController
 */
@RestController
@Api(value = "Company Controller", description = "Company Related Endpoints", tags = "Company Controller")
public class CompanyController {

    public CompanyServiceImpl companyServiceImpl;
    public CompanyDTOToCompany CompanyDTOToCompany;
    public CompanyToDTO companyToDTO;

    @Autowired
    public CompanyController(CompanyServiceImpl companyServiceImpl, CompanyDTOToCompany CompanyDTOToCompany, CompanyToDTO companyToDTO){
        this.companyServiceImpl = companyServiceImpl;
        this.CompanyDTOToCompany = CompanyDTOToCompany;
        this.companyToDTO = companyToDTO;
    }

    @PostMapping("company")
    public Mono<CompanyDTO> createCompany(@ModelAttribute CompanyDTO companyDTO, BindingResult bindingResults){
        return companyServiceImpl.saveCompany(CompanyDTOToCompany.convert(companyDTO)).map(companyToDTO::convert);
    }
    
}