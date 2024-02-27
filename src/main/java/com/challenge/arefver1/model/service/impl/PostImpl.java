package com.challenge.arefver1.model.service.impl;

import com.challenge.arefver1.model.dao.PostDao;
import com.challenge.arefver1.model.dto.CommentDto;
import com.challenge.arefver1.model.dto.PostDto;
import com.challenge.arefver1.model.entities.Post;
import com.challenge.arefver1.model.service.IPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Component
public class PostImpl implements IPost {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final RestTemplate restTemplate;

    @Autowired
    public PostImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<PostDto> getPostsByUserId(Long id) {
        String url = BASE_URL + "/posts?userId=" + id;
        PostDto[] posts = restTemplate.getForObject(url, PostDto[].class);
        return Arrays.asList(posts);
    }

    @Override
    public List<PostDto> searchPostsByBody(String body) {
        String url = BASE_URL + "/posts?q=" + body;
        PostDto[] posts = restTemplate.getForObject(url, PostDto[].class);
        assert posts != null;
        return Arrays.asList(posts);
    }

    @Override
    public int countCommentsByPostId(Long Id) {
        String url = BASE_URL + "/comments?postId=" + Id;
        CommentDto[] comments = restTemplate.getForObject(url, CommentDto[].class);
        return comments != null ? comments.length : 0;
    }

    @Override
    public List<PostDto> getTop10MostCommentedPosts() {

        try {
            // Obtener todos los comentarios
            String commentsUrl = BASE_URL + "/comments";
            CommentDto[] comments = restTemplate.getForObject(commentsUrl, CommentDto[].class);

            // Contar la cantidad de comentarios por postId
            Map<Long, Long> postIdToCommentCount = new HashMap<>();
            for (CommentDto comment : comments) {
                Long id = comment.getPost().getId();
                postIdToCommentCount.put(id, postIdToCommentCount.getOrDefault(id, 0L) + 1);
            }

            // Ordenar los postId por cantidad de comentarios en orden descendente
            List<Map.Entry<Long, Long>> sortedPostIdToCommentCount = postIdToCommentCount.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toList());

            // Obtener los 10 postId con más comentarios
            List<Long> top10PostIds = sortedPostIdToCommentCount.stream()
                    .limit(10)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            // Obtener los detalles de los 10 posts más comentados
            List<PostDto> top10MostCommentedPosts = new ArrayList<>();
            for (Long postId : top10PostIds) {
                String postUrl = BASE_URL + "/posts/" + postId;
                PostDto post = restTemplate.getForObject(postUrl, PostDto.class);
                top10MostCommentedPosts.add(post);
            }

            return top10MostCommentedPosts;
        } catch (Exception e) {
            // Manejar cualquier excepción que ocurra durante la ejecución
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    }