package com.challenge.arefver1.model.service.impl;

import com.challenge.arefver1.model.dao.PostDao;
import com.challenge.arefver1.model.entities.Post;
import com.challenge.arefver1.model.service.IPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PostImpl implements IPost {
    private final PostDao postDao;

    @Autowired
    public PostImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Transactional
    @Override
    public Post savePost(Post post) {
        return postDao.save(post);
    }

    @Transactional(readOnly = true)
    @Override
    public Post FindPosByid(Long id) {
        return postDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void DeletePost(Post post) {
        postDao.delete(post);

    }
}