package com.challenge.arefver1.controller;
import com.challenge.arefver1.model.entities.Comment;
import com.challenge.arefver1.model.service.IComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Comments")
public class CommentController {
    private  final IComment commentService;

    @Autowired
    public CommentController(IComment commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment){return commentService.saveComment(comment);}

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment upddateComment(@RequestBody Comment comment){return commentService.saveComment(comment);}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Comment commentDeleted = commentService.findCommentByid(id);
            commentService.deleteComment(commentDeleted);
            return new ResponseEntity<>(commentDeleted, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt){
            response.put("mensaje", exDt.getMessage());
            response.put("comment", null);
        }return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment ShowCommentById(Long id){return commentService.findCommentByid(id);}


}

