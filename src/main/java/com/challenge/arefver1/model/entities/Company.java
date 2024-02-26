package com.challenge.arefver1.model.entities;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Embeddable
@Table(name="company")
public class Company {

    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String catchPhrase;
    @Column
    private String bs;
}
