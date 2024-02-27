package com.challenge.arefver1.model.entities;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String userName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String website;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}
