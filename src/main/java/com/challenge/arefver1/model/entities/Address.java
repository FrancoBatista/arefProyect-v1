package com.challenge.arefver1.model.entities;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Builder
@Embeddable
@Table(name = "address")
public class Address {
    @Id
    private Long id;
    @Column
    private String street;
    @Column
    private String suite;
    @Column
    private String city;
    @Column
    private String zipCode;
    @Column
    private Geo geo;
}

