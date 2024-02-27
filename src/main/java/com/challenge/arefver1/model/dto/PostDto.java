package com.challenge.arefver1.model.dto;

import com.challenge.arefver1.model.entities.User;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
public class PostDto implements Serializable{
    private Long id;
    private User user;
    private String title;
    private String body;
}
