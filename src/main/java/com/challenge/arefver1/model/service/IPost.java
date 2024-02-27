package com.challenge.arefver1.model.service;

import com.challenge.arefver1.model.dto.PostDto;
import com.challenge.arefver1.model.entities.Post;

import java.util.List;

public interface IPost {

    List<Post> listAlll();
    Post savePost(PostDto post);

    Post FindPosByid(Long id);

    void DeletePost(Post post);

    boolean existsById(Long id);
}
