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
