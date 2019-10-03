package com.celcsa.payroll.services;

import java.util.Date;

import com.celcsa.payroll.domain.schedule.Shift;
import com.celcsa.payroll.repositories.ShiftRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ShiftServiceImpl
 */
@Service
public class ShiftServiceImpl implements IShiftService {

    public ShiftRepository shiftRepository;

    public ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public ShiftServiceImpl(ShiftRepository shiftRepository, ReactiveMongoTemplate mongoTemplate){
        this.shiftRepository = shiftRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Shift> saveShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public Flux<Shift> getShiftsBy(Date from, Date to, String projectName) {
        final Criteria c = Criteria.where("projectName").is(projectName);
        c.andOperator(Criteria.where("from").gte(from).and("to").lte(to));
        final Query q = new Query(c);
        q.with(new Sort(Sort.Direction.ASC, "from"));
        return mongoTemplate.find( q, Shift.class);
    }

    
}