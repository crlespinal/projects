package com.celcsa.payroll.domain.employee.projects;

import lombok.Builder;
import lombok.Data;

/**
 * ProjectLength
 */
@Data
@Builder
public class ProjectLength {

    private int lengthInDays;

    private WorkingProjectStatus workingProjectStatus;

}