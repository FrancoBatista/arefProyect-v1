package com.challenge.arefver1.model.service;

import com.challenge.arefver1.model.entities.Comment;

public interface IComment {

    Comment saveComment(Comment comment);

    Comment findCommentByid(Long id);

    void deleteComment(Comment comment);
}
