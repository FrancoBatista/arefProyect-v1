package com.challenge.arefver1.model.service.impl;

import com.challenge.arefver1.model.dao.CommentDao;
import com.challenge.arefver1.model.entities.Comment;
import com.challenge.arefver1.model.service.IComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
public class CommentImpl implements IComment {

    private final CommentDao commentdao;

    @Autowired
    public CommentImpl(CommentDao commentdao) {
        this.commentdao = commentdao;
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        return commentdao.save(comment);
    }

    @Transactional
    @Override
    public Comment findCommentByid(Long id) {
        return commentdao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteComment(Comment comment) {
        commentdao.delete(comment);
    }
}