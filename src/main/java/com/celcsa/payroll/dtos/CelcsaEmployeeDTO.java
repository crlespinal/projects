package com.celcsa.payroll.dtos;

import javax.validation.constraints.NotNull;

import com.celcsa.payroll.domain.employee.projects.WorkingProjectList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * CelcsaEmployeeDTO
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CelcsaEmployeeDTO {

    @NonNull
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String lastName;
    @NotNull
    private String firstName;
    private String middleName;
    
}