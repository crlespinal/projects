package com.celcsa.payroll.converters;

import com.celcsa.payroll.domain.employee.CelcsaEmployee;
import com.celcsa.payroll.domain.employee.FirstName;
import com.celcsa.payroll.domain.employee.LastName;
import com.celcsa.payroll.domain.employee.MiddleName;
import com.celcsa.payroll.domain.employee.Username;
import com.celcsa.payroll.dtos.CelcsaEmployeeDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.Synchronized;

/**
 * CelcsaEmployeeToDTO
 */
@Component
public class CelcsaEmployeeDTOToEmployee implements Converter<CelcsaEmployeeDTO, CelcsaEmployee> {

    @Synchronized
    @Override
    public CelcsaEmployee convert(CelcsaEmployeeDTO source) {

        CelcsaEmployee e = new CelcsaEmployee(new Username(source.getUsername().toLowerCase()), new FirstName(StringUtils.capitalize(source.getFirstName())),
                new LastName(StringUtils.capitalize(source.getLastName())), new MiddleName(StringUtils.capitalize(source.getMiddleName())));
        return e;
    }

}