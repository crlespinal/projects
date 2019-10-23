package com.celcsa.payroll.services;

import com.celcsa.payroll.domain.Company;

import reactor.core.publisher.Mono;

/**
 * ICompanyService
 */
public interface ICompanyService {

    public Mono<Company> saveCompany(Company Company);
    
}