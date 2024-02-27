package com.challenge.arefver1.model.dto;

import com.challenge.arefver1.model.entities.Address;
import com.challenge.arefver1.model.entities.Company;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
public class UserDto implements Serializable {

    private Long id;

    private String name;


    private String userName;


    private String email;


    private String phone;


    private String website;


    private Company company;


    private Address address;
}
