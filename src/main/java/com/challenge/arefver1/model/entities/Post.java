package com.challenge.arefver1.model.entities;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Builder
@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String title;

    @Column
    private String body;

}
