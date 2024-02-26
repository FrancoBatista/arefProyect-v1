package com.challenge.arefver1.model.dao;

import com.challenge.arefver1.model.entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentDao extends CrudRepository<Comment, Long> {
}
