package com.celcsa.payroll.domain.schedule;

import java.util.ArrayList;
import java.util.List;

public class ShiftList extends ArrayList<Shift>{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ShiftList(){
        super();
    }

    public ShiftList(List<Shift> shits){
        super(shits);
    }
    
}