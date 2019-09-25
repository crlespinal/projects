package com.celcsa.payroll.converters;

import com.celcsa.payroll.domain.employee.CelcsaEmployee;
import com.celcsa.payroll.domain.employee.FirstName;
import com.celcsa.payroll.domain.employee.LastName;
import com.celcsa.payroll.domain.employee.MiddleName;
import com.celcsa.payroll.domain.employee.Username;
import com.celcsa.payroll.dtos.CelcsaEmployeeDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.Synchronized;

/**
 * CelcsaEmployeeToDTO
 */
@Component
public class CelcsaEmployeeDTOToEmployee implements Converter<CelcsaEmployeeDTO, CelcsaEmployee> {

    @Synchronized
    @Override
    public CelcsaEmployee convert(CelcsaEmployeeDTO source) {

        CelcsaEmployee e = new CelcsaEmployee(new Username(source.getUsername()), new FirstName(source.getFirstName()),
                new LastName(source.getLastName()), new MiddleName(source.getMiddleName()));
        return e;
    }

}