package com.celcsa.payroll.dtos;

import java.util.List;

import com.celcsa.payroll.domain.employee.projects.WorkingProjectStatus;
import com.celcsa.payroll.domain.schedule.Shift;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * WorkingProjectDTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkingProjectDTO {

     private String employeeId;
     private String projectName;
     private int projectDuration;
     private int estimatedDuration;
     private WorkingProjectStatus workingProjectStatus;
     private List<Shift> shifts;

     public boolean isValid(){
          return !StringUtils.isEmpty(this.projectName) &&
          projectDuration > 0 &&
          workingProjectStatus !=null;
     }

}