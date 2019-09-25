package com.celcsa.payroll.domain.employee;

import java.util.ArrayList;
import java.util.List;

import com.celcsa.payroll.domain.base.BaseEntity;
import com.celcsa.payroll.domain.employee.projects.WorkingProject;
import com.celcsa.payroll.domain.employee.projects.WorkingProjectList;
import com.celcsa.payroll.domain.employee.projects.WorkingProjectStatus;

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

            WorkingProject p = new WorkingProject("Construction Dadeland", 90, 120, WorkingProjectStatus.CREATED);
            WorkingProject p2 = new WorkingProject("Construction Dadeland South", 90, 120, WorkingProjectStatus.CREATED);

            workingProjects.add(p);
            workingProjects.add(p2);
    }

    public CelcsaEmployee(String id, WorkingProject workingProject){
        this.setId(id);
        workingProjects.add(workingProject);
    }

    public void addWorkingProject(WorkingProject workingProject){
        workingProjects.add(workingProject);
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