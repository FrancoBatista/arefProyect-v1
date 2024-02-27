package com.challenge.arefver1.model.service;

import com.challenge.arefver1.model.dto.PostDto;
import com.challenge.arefver1.model.entities.Post;

import java.util.List;

public interface IPost {

    // Consulta de artículos por usuario
    List<PostDto> getPostsByUserId(Long userId);

    // Búsqueda de artículos por texto del body
    List<PostDto> searchPostsByBody(String searchText);

    // Métricas de los artículos: Cantidad de comentarios por artículo publicado
    int countCommentsByPostId(Long postId);

    // Reportes: Top 10 de artículos mas comentados ordenados de mayor cantidad de comentarios a menor
    List<PostDto> getTop10MostCommentedPosts();
}
