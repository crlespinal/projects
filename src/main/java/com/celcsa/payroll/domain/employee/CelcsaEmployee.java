package com.celcsa.payroll.domain.employee;

import java.util.ArrayList;
import java.util.List;

import com.celcsa.payroll.domain.base.BaseEntity;
import com.celcsa.payroll.domain.employee.projects.WorkingProject;
import com.celcsa.payroll.exceptions.ExistingProjectException;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Employee
 */
@Document(collection = "employee")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CelcsaEmployee extends BaseEntity implements IEmployee {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String username;
    private String lastName;
    private String firstName;
    private String middleName;

    private List<WorkingProject> workingProjects = new ArrayList<>();

    public CelcsaEmployee(Username username,FirstName firstName,LastName lastName, MiddleName middleName){
        this.username = username.getUsername();
        this.firstName = firstName.getFirstName();
        this.lastName = lastName.getLastname();
        if(middleName!=null)
            this.middleName = middleName.getMiddleName();
    }

    public CelcsaEmployee(String id, WorkingProject workingProject){
        this.setId(id);
        workingProjects.add(workingProject);
    }

    public void addWorkingProject(WorkingProject workingProject){
        if(projectExist(workingProject))
            throw new ExistingProjectException("Project: " + workingProject.getProjectName() + " already exist.");
        workingProjects.add(workingProject);
    }

    protected boolean projectExist(WorkingProject workingProject) {
        List<WorkingProject> list = this.getWorkingProjects();
        for(WorkingProject wp:list)
            if(wp.compareTo(workingProject)==1)
                return true;
        return false;
    }

    @Override
    public String getUsername() {
       return this.username;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getMiddleName() {
        return this.middleName;
    }

    
}