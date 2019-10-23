package com.celcsa.payroll.dtos;

import java.util.ArrayList;
import java.util.List;

import com.celcsa.payroll.items.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CompanyDTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyDTO {

    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private List<Address> alternateAddresses = new ArrayList<>();
    private List<String> alternateCompanyNames = new ArrayList<>();
    
}