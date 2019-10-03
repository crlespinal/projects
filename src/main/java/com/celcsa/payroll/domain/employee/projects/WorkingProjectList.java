package com.celcsa.payroll.domain.employee.projects;

import java.util.ArrayList;
import java.util.List;

import lombok.Synchronized;

public class WorkingProjectList extends ArrayList<WorkingProject> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public WorkingProjectList(){
        super();
    }

    public WorkingProjectList(List<WorkingProject> list){
        super(list);
    }

    @Synchronized
    public void addProject(WorkingProject workingProject) {
        for (WorkingProject wp : this)
            if (!wp.equals(workingProject))
                this.add(workingProject);
    }

}