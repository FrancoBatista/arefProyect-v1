package com.challenge.arefver1.controller;
import com.challenge.arefver1.model.entities.Post;
import com.challenge.arefver1.model.service.IPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Posts")
public class PostController {
    private final IPost postService;

    @Autowired
    public PostController(IPost postService) {this.postService = postService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post){return postService.savePost(post);}

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post upddatePost (@RequestBody Post post){return postService.savePost(post);}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        Map<String, Object> responsePost = new HashMap<>();
        try {
            Post postDeleted = postService.FindPosByid(id);
            postService.DeletePost(postDeleted);
            return new ResponseEntity<>(postDeleted, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            responsePost.put("mensaje", exDt.getMessage());
            responsePost.put("Post", null);
        }return  new ResponseEntity<>(responsePost,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post showPostById(@PathVariable Long id){return postService.FindPosByid(id);}
}
