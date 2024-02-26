package com.challenge.arefver1.model.dao;

import com.challenge.arefver1.model.entities.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostDao extends CrudRepository<Post, Long> {
}
