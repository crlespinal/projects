package com.celcsa.payroll.controllers;

import javax.validation.Valid;

import com.celcsa.payroll.converters.ShiftToDTO;
import com.celcsa.payroll.domain.schedule.Shift;
import com.celcsa.payroll.dtos.ShiftDTO;
import com.celcsa.payroll.exceptions.InvalidParameterException;

import com.celcsa.payroll.services.ShiftServiceImpl;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ShiftController
 */
@RestController
public class ShiftController {

    
    public ShiftToDTO shiftToDTO;
    public ShiftServiceImpl shiftServiceImpl;

    public ShiftController( ShiftToDTO shiftToDTO, ShiftServiceImpl shiftServiceImpl) {
       
        this.shiftToDTO = shiftToDTO;
        this.shiftServiceImpl = shiftServiceImpl;
    }

    @PostMapping("shift")
    public Flux<ShiftDTO> getShifts(@ModelAttribute ShiftDTO dto, BindingResult bindingResults) {
        if (bindingResults.hasErrors() || !dto.isValid())
            throw new InvalidParameterException("All params required");
        try {
            return shiftServiceImpl.getShiftsBy( dto.getFrom(), dto.getTo(), dto.getProjectName()).map(shiftToDTO::convert)
            .defaultIfEmpty(new ShiftDTO());    
        } catch (Exception e) {
            throw e;
        }
        
    }

    /*@PostMapping("/shift/add")
    public Mono<ShiftDTO> addShiftToEmployee(@Valid @ModelAttribute ShiftDTO dto, BindingResult bindingResults) {
        if(bindingResults.hasErrors() || !dto.isValid())
            throw new InvalidParameterException("All params required");
        try{
            return shiftService.save(new Shift(dto.getEmployeeId(), dto.getFrom(), dto.getTo(), dto.getProjectName())).map(shiftToDTO::convert);
        } catch(Exception e){
            throw e;
        }
    }*/

}