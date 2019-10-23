
package com.celcsa.payroll.services;

import com.celcsa.payroll.domain.Company;
import com.celcsa.payroll.exceptions.CompanyExistException;
import com.celcsa.payroll.repositories.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

/**
 * CompanyServiceImpl
 */
@Service
public class CompanyServiceImpl implements ICompanyService {

    public CompanyRepository companyRepository;
    public ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ReactiveMongoTemplate mongoTemplate) {
        this.companyRepository = companyRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Company> saveCompany(Company company) {
        return mongoTemplate
                .find(Query.query(Criteria.where("name").is(company.getName())
                        .orOperator(Criteria.where("alternateCompanyNames").is(company.getName()))), Company.class)
                .collectList().flatMap(item -> {
                    if (item.size() > 0)
                        throw new CompanyExistException();
                    return companyRepository.save(company);
                });
    }
}