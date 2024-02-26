package com.challenge.arefver1.model.service;

import com.challenge.arefver1.model.entities.Post;

public interface IPost {
    Post savePost(Post post);

    Post FindPosByid(Long id);

    void DeletePost(Post post);
}
