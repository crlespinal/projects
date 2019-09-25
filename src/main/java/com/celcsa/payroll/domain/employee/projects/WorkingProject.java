package com.celcsa.payroll.domain.employee.projects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * WorkingProject
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkingProject implements IProject{

    private String projectName;

    private int projectDuration;

    private int estimatedProjectDuration;

    private WorkingProjectStatus workingProjectStatus;

    public WorkingProject(WorkingProject workingProject){
        this.projectName = workingProject.getProjectName();
    }

    @Override
    public String getProjectName() {
       return this.projectName;
    }

    
}