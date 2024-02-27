package com.challenge.arefver1.controller;
import com.challenge.arefver1.model.dto.PostDto;
import com.challenge.arefver1.model.entities.Post;
import com.challenge.arefver1.model.payload.MensajeResponse;
import com.challenge.arefver1.model.service.IPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Posts")
public class PostController {
    private final IPost postService;

    @Autowired
    public PostController(IPost postService) {this.postService = postService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Long id) {
        List<PostDto> posts = postService.getPostsByUserId(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> searchPostsByBody(@RequestParam String body) {
        List<PostDto> posts = postService.searchPostsByBody(body);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/top10")
    public ResponseEntity<List<PostDto>> getTop10MostCommentedPosts() {
        List<PostDto> posts = postService.getTop10MostCommentedPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
