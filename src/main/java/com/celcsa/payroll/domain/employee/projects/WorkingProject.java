package com.celcsa.payroll.domain.employee.projects;

import java.util.ArrayList;
import java.util.List;

import com.celcsa.payroll.domain.base.BaseEntity;
import com.celcsa.payroll.domain.schedule.Shift;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

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
@Document(collection = "projects")
public class WorkingProject extends BaseEntity implements IProject, Comparable<WorkingProject>{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Indexed(unique = true)
    @TextIndexed
    private String projectName;

    private int projectDuration;

    private int estimatedProjectDuration;

    private WorkingProjectStatus workingProjectStatus;

    private List<Shift> shifts = new ArrayList<>();

    public WorkingProject(String projectName,int projectDuration,int estimatedProjectDuration,WorkingProjectStatus workingProjectStatus){
        this.projectName = projectName;
        this.projectDuration = projectDuration;
        this.estimatedProjectDuration = estimatedProjectDuration;
        this.workingProjectStatus = workingProjectStatus;
    }

    public WorkingProject(WorkingProject workingProject){
        this.projectName = workingProject.getProjectName();
    }

    public void addShift(Shift shift){
        shifts.add(shift);
    }

    @Override
    public String getProjectName() {
       return this.projectName;
    }

    public boolean isValid(){
        return !StringUtils.isEmpty(projectName);
    }

    @Override
    public int compareTo(WorkingProject o) {
        if(this.getProjectName().equalsIgnoreCase(o.getProjectName()))
            return 1;
        return 0;
    }

    
}