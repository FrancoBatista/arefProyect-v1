package com.challenge.arefver1.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "geo")
@Embeddable
public class Geo {
    @Id
    private Long id;
    @Column
    private Double lat;
    @Column
    private double lng;
}
