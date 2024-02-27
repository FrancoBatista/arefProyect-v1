package com.challenge.arefver1.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class CompanyDto implements Serializable {
    private Long id;

    private String name;

    private String catchPhrase;

    private String bs;
}
