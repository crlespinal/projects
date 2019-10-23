package com.celcsa.payroll.dtos;

import java.util.List;

import com.celcsa.payroll.domain.employee.projects.WorkingProjectStatus;
import com.celcsa.payroll.domain.schedule.Shift;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
     
     private String projectName;
     private int projectDuration;
     private int estimatedDuration;
     private WorkingProjectStatus workingProjectStatus;

     @JsonIgnore
     public boolean isValid(){
          return !StringUtils.isEmpty(this.projectName) &&
          projectDuration > 0 &&
          workingProjectStatus !=null;
     }

}