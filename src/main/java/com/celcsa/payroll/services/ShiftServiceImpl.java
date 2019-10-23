package com.celcsa.payroll.services;

import java.util.Date;
import java.util.Iterator;

import com.celcsa.payroll.domain.schedule.Shift;
import com.celcsa.payroll.exceptions.ShiftConlictException;
import com.celcsa.payroll.items.DateRange;
import com.celcsa.payroll.repositories.ShiftRepository;
import com.celcsa.payroll.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ShiftServiceImpl
 */
@Service
@Slf4j
public class ShiftServiceImpl implements IShiftService {

    public ShiftRepository shiftRepository;

    public ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public ShiftServiceImpl(ShiftRepository shiftRepository, ReactiveMongoTemplate mongoTemplate) {
        this.shiftRepository = shiftRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Shift> saveShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public Flux<Shift> getShiftsBy(String employeeId, Date from, Date to, String projectName) {
        final Criteria c = Criteria.where("projectName").is(projectName).and("employeeId").is(employeeId);
        c.andOperator(Criteria.where("from").gte(from).and("to").lte(to));
        final Query q = new Query(c);
        q.with(new Sort(Sort.Direction.ASC, "from"));
        return mongoTemplate.find(q, Shift.class);
    }

    @Override
    public Flux<Shift> getShiftsByEmployeeDatesAndProject(String employeeId, Date from, Date to, String projectId) {
        final Criteria c = Criteria.where("projectId").is(projectId).and("employeeId").is(employeeId);
        c.andOperator(Criteria.where("from").gte(from).and("to").lte(to));
        final Query q = new Query(c);
        q.with(new Sort(Sort.Direction.ASC, "from"));
        return mongoTemplate.find(q, Shift.class);
    }

    @Override
    public Mono<Shift> saveShiftCheckConflicts(Shift shift) {

        final DateRange target = new DateRange(shift.getFrom(), shift.getTo());

        log.debug("*** Target Shift: " + target);

        return getShiftsByEmployeeDatesAndProject(shift.getEmployeeId(), DateUtil.toBeginningOfDate(shift.getFrom()),
                DateUtil.toEndOfDate(shift.getTo()), shift.getProjectId()).collectList().flatMap(item -> {

                    final Iterator<Shift> iterator = item.iterator();

                    while (iterator.hasNext()) {

                        final Shift sourceShift = iterator.next();

                        log.debug("*** Running Current Shift: " + sourceShift);

                        final DateRange source = new DateRange(sourceShift.getFrom(), sourceShift.getTo());

                        if(sourceShift.equals(shift))    
                            throw new ShiftConlictException("Shift Conflict.");
                        if (DateUtil.isTargetInSource(source, target, false))
                            throw new ShiftConlictException("Shift Conflict.");
                        if (DateUtil.isTargetInSource(source, target, true))
                            throw new ShiftConlictException("Shift Conflict.");
                    }
                    return saveShift(shift);
                });
    }

}