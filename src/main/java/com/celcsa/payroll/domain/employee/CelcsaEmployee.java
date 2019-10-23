package com.celcsa.payroll.domain.employee;

import java.util.ArrayList;
import java.util.List;

import com.celcsa.payroll.domain.base.BaseEntity;
import com.celcsa.payroll.exceptions.ExistingProjectException;
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

    private List<String> workingProjects = new ArrayList<>();

    public CelcsaEmployee(Username username,FirstName firstName,LastName lastName, MiddleName middleName){
        this.username = username.getUsername();
        this.firstName = firstName.getFirstName();
        this.lastName = lastName.getLastname();
        if(middleName!=null)
            this.middleName = middleName.getMiddleName();
    }

    public CelcsaEmployee(String projectName){
        workingProjects.add(projectName);
    }

    public void addWorkingProject(String projectName){
        if(projectExist(projectName))
            throw new ExistingProjectException("Project: " + projectName + " already exist.");
        workingProjects.add(projectName);
    }

    protected boolean projectExist(String projectName) {
        List<String> list = this.getWorkingProjects();
        for(String wp:list)
            if(wp.compareTo(projectName)==1)
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