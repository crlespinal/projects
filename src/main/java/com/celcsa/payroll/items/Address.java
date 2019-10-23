package com.celcsa.payroll.items;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Address
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Address {

    @NonNull
    private String address;
    private String address2;
    @NonNull
    private String city;
    @NonNull
    private String state;
    @NonNull
    private String zipcode;

    public Address(Address address){
        this.address = address.getAddress();
        this.address2 = address.getAddress2();
        this.city = address.getCity();
        this.state = address.getState();
        this.zipcode = address.getZipcode();
    }

    public String getFullAddress(){
        return StringUtils.capitalize(this.address) + " " + (StringUtils.isEmpty(this.address2) ? "" : StringUtils.capitalize(this.address2)) 
        + " " + StringUtils.capitalize(this.city) + ", " + StringUtils.capitalize(this.state) + " " + this.zipcode;
    }

    @Override
    public boolean equals(Object obj){
        final boolean isInstanceOf = obj instanceof Address;
        if(!isInstanceOf)return false;
        final Address a = (Address) obj;
        final String address = a.getAddress().trim().toLowerCase();
        final String address2 = StringUtils.isEmpty(a.getAddress2()) ? "" : a.getAddress2().trim().toLowerCase();
        final String city = a.getCity().trim().toLowerCase();
        final String state = a.getState().trim().toLowerCase();
        final String zipcode = a.getZipcode().trim().toLowerCase();
        final String tAddress = this.getAddress().trim().toLowerCase();
        final String tAddress2 = StringUtils.isEmpty(this.getAddress2()) ? "" : this.getAddress2().trim().toLowerCase();
        final String tCity = this.getCity().trim().toLowerCase();
        final String tState = this.getState().trim().toLowerCase();
        final String tZipcode = this.getZipcode().trim().toLowerCase();
        return tAddress.equals(address) && tAddress2.equals(address2) && tCity.equals(city) && tState.equals(state) && tZipcode.equals(zipcode); 
    }

}