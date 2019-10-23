package com.celcsa.payroll.controllers;

import javax.validation.Valid;

import com.celcsa.payroll.converters.ShiftToDTO;
import com.celcsa.payroll.domain.schedule.Shift;
import com.celcsa.payroll.dtos.ShiftDTO;
import com.celcsa.payroll.exceptions.InvalidParameterException;
import com.celcsa.payroll.exceptions.ShiftExistException;
import com.celcsa.payroll.services.ShiftServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import org.springframework.http.HttpStatus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ShiftController
 */
@RestController
@Api(value = "Celcsa Shifts Controller", description = "Celcsa Shifts Related Endpoints", tags = "Celcsa Shifts Controller")
public class ShiftController {

    public ShiftToDTO shiftToDTO;
    public ShiftServiceImpl shiftServiceImpl;

    @Autowired
    public ShiftController(ShiftToDTO shiftToDTO, ShiftServiceImpl shiftServiceImpl) {
        this.shiftToDTO = shiftToDTO;
        this.shiftServiceImpl = shiftServiceImpl;
    }

    @PostMapping("shift")
    public Flux<ShiftDTO> getShifts(@ModelAttribute ShiftDTO dto, BindingResult bindingResults) {
        if (bindingResults.hasErrors() || !dto.isValid())
            throw new InvalidParameterException("All params required");
        try {
            return shiftServiceImpl.getShiftsBy(dto.getEmployeeId(),dto.getFrom(), dto.getTo(), dto.getProjectId())
                    .map(shiftToDTO::convert).defaultIfEmpty(new ShiftDTO());
        } catch (Exception e) {
            throw e;
        }

    }

    @PostMapping("/shift/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ShiftDTO> addShiftToEmployee(@Valid @ModelAttribute ShiftDTO dto, BindingResult bindingResults) {
        if (bindingResults.hasErrors() || !dto.isValid())
            throw new InvalidParameterException("All params required");
        try {
            if (dto == null || !dto.isValid())
                throw new InvalidParameterException("All params required");
            return shiftServiceImpl.saveShiftCheckConflicts(new Shift(dto.getEmployeeId(), dto.getFrom(), dto.getTo(), dto.getProjectId())).map(shiftToDTO::convert);
        } catch (Exception e) {
            throw e;
        }
    }

}