package com.celcsa.payroll.domain;

import java.util.ArrayList;
import java.util.List;

import com.celcsa.payroll.domain.base.BaseEntity;
import com.celcsa.payroll.items.Address;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Company
 */
@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Company extends BaseEntity{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Indexed(unique = true)
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    private List<Address> alternateAddresses = new ArrayList<>();
    private List<String> alternateCompanyNames = new ArrayList<>();

    public void addAddress(Address address){
        if(alternateAddresses.stream().filter(s->s.equals(address)).count()>0)
            return;
        alternateAddresses.add(address);
    }

    public void addCompanyName(String companyName){
        if(alternateCompanyNames.stream().filter(s->s.equals(companyName)).count()>0)
        return;
        alternateCompanyNames.add(companyName);
    }
}