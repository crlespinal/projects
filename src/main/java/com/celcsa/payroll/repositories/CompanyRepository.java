package com.celcsa.payroll.repositories;

import com.celcsa.payroll.domain.Company;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * CompanyRepository
 */
@Repository
public interface CompanyRepository extends ReactiveMongoRepository<Company, String>{

    
}