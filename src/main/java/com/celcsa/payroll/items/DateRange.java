package com.celcsa.payroll.items;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DateRange
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class DateRange {

    private Date from;
    private Date to;

}