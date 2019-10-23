package com.celcsa.payroll.services;

import java.util.Date;

import com.celcsa.payroll.domain.schedule.Shift;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * IShiftService
 */
public interface IShiftService {

    public Mono<Shift> saveShift(Shift shift);

    public Mono<Shift> saveShiftCheckConflicts(Shift shift);

    public Flux<Shift> getShiftsBy(String employeeId, Date from, Date to, String projectName);

    public Flux<Shift> getShiftsByEmployeeDatesAndProject(String employeeId, Date from, Date to, String projectId);

}