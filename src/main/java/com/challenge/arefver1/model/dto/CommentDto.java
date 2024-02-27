package com.challenge.arefver1.model.dto;
import com.challenge.arefver1.model.entities.Post;
import com.challenge.arefver1.model.entities.User;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
@Data
@ToString
@Builder
public class CommentDto implements Serializable {

    private Long id;


    private String name;

    private String email;

    private String body;


    private Post post;


}
