package com.celcsa.payroll.domain.schedule;

import java.util.Date;

import lombok.Getter;

@Getter
public abstract class PayrollView {

    private String projectName;
    private Date from;
    private Date to;
    private boolean loadData;

   public PayrollView(String projectName, Date from, Date to, boolean loadData){
        this.projectName = projectName;
        this.from = from;
        this.to = to;
        this.loadData = loadData;

   }

   public abstract void loadData();
    
}