package com.celcsa.payroll.repositories;

import com.celcsa.payroll.domain.employee.CelcsaEmployee;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

/**
 * CelcsaEmployeeRepository
 */
@Repository
public interface CelcsaEmployeeRepo extends ReactiveMongoRepository<CelcsaEmployee, String>{

    Mono<CelcsaEmployee> findByUsernameLike(String username);

    Mono<CelcsaEmployee> findById(String id);
    
}