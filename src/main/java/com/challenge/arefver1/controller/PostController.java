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

    @GetMapping()
    public ResponseEntity<?> showAll() {
        List<Post> getList = postService.listAlll();
        if (getList == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mnesaje("No hay registros")
                            .object(null)
                            .build()
                    , HttpStatus.OK);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mnesaje("")
                        .object(getList)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody PostDto postDto) {
        Post postSave = null;
        try {
            postSave = postService.savePost(postDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mnesaje("Guardado correctamente")
                    .object(postDto.builder()
                            .id(postDto.getId())
                            .body(postDto.getBody())
                            .title(postDto.getTitle())
                            .user(postDto.getUser())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mnesaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PostDto postDto, @PathVariable Long id) {
        Post postUpdate = null;
        try {
            if (postService.existsById(id)) {
                postDto.setId(id);
                postUpdate = postService.savePost(postDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mnesaje("Guardado correctamente")
                        .object(PostDto.builder()
                                .id(postUpdate.getId())
                                .title(postUpdate.getTitle())
                                .body(postUpdate.getBody())
                                .user(postUpdate.getUser())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mnesaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mnesaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Post postDelete = postService.FindPosByid(id);
            postService.DeletePost(postDelete);
            return new ResponseEntity<>(postDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mnesaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Post post = postService.FindPosByid(id);

        if (post == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mnesaje("El registro que intenta buscar, no existe!!")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mnesaje("")
                        .object(PostDto.builder()
                                .title(post.getTitle())
                                .body(post.getBody())
                                .user(post.getUser())
                                .id(post.getId())
                                .build())
                        .build()
                , HttpStatus.OK);
    }

}
