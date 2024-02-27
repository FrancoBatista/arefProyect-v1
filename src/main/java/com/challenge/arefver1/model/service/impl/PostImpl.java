package com.challenge.arefver1.model.service.impl;

import com.challenge.arefver1.model.dao.PostDao;
import com.challenge.arefver1.model.dto.PostDto;
import com.challenge.arefver1.model.entities.Post;
import com.challenge.arefver1.model.service.IPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PostImpl implements IPost {
    private final PostDao postDao;

    @Autowired
    public PostImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public List<Post> listAlll() {
        return (List) postDao.findAll();

    }

    @Transactional
    @Override
    public Post savePost(PostDto postDto) {
        Post post = Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .body(postDto.getBody())
                .user(postDto.getUser())
                .build();


        return postDao.save(post);
    }

    @Transactional(readOnly = true)
    @Override
    public Post FindPosByid(Long id) {return postDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void DeletePost(Post post) {
        postDao.delete(post);

    }

    @Override
    public boolean existsById(Long id) {
        return postDao.existsById(id);
    }
}