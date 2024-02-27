package com.challenge.arefver1.model.dto;

import java.io.Serializable;

import com.challenge.arefver1.model.entities.Geo;
import lombok.*;


@Data
@ToString
@Builder
public class AdressDto implements Serializable {

    private Long id;
    private String street;
    private String suite;
    private String city;

    private String zipCode;

    private Geo geo;
}
