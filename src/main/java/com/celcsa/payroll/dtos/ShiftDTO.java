package com.celcsa.payroll.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * ShiftDTO
 */
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ShiftDTO {

    private String employeeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date from;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date to;

    @NonNull
    private String projectName;

    public boolean isValid(){
        return employeeId!=null && from!=null && to!=null && projectName!=null;
    }
    
}