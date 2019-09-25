package com.celcsa.payroll.domain.employee.projects;

import java.util.ArrayList;

import lombok.Synchronized;

public class WorkingProjectList extends ArrayList<WorkingProject> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Synchronized
    public void addProject(WorkingProject workingProject) {
        for (WorkingProject wp : this)
            if (!wp.equals(workingProject))
                this.add(workingProject);
    }

}