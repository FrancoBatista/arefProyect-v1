package com.challenge.arefver1.model.entities;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name= "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String body;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
