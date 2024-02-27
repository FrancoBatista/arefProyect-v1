package com.challenge.arefver1.model.service;

import com.challenge.arefver1.model.dto.CommentDto;
import com.challenge.arefver1.model.entities.Comment;

import java.util.List;

public interface IComment {

    List<Comment> listAlll();

    Comment saveComment(CommentDto comment);

    Comment findCommentByid(Long id);

    void deleteComment(Comment comment);

    boolean existsById(Long id);
}
