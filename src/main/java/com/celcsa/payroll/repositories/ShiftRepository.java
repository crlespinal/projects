package com.celcsa.payroll.repositories;

import java.util.Date;

import com.celcsa.payroll.domain.schedule.Shift;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

/**
 * ShiftRepository
 */
@Repository
public interface ShiftRepository extends ReactiveMongoRepository<Shift, String>{

    @Query("{'userId': ?0, 'from' : { $gte: ?1 }, 'to' : { $lte: ?2 } }")        
    public Flux<Shift> findByEmployeeIdAndFromToDate(String id, Date from, Date to);
    
}