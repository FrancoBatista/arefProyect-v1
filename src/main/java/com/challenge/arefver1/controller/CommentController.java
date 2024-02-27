package com.challenge.arefver1.controller;
import com.challenge.arefver1.model.dto.CommentDto;
import com.challenge.arefver1.model.entities.Comment;
import com.challenge.arefver1.model.payload.MensajeResponse;
import com.challenge.arefver1.model.service.IComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Comments")
public class CommentController {
    private  final IComment commentService;

    @Autowired
    public CommentController(IComment commentService) {
        this.commentService = commentService;
    }

    @GetMapping()
    public ResponseEntity<?> showAll() {
        List<Comment> getList = commentService.listAlll();
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


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CommentDto commentDto) {
        Comment commentSave = null;
        try {
            commentSave = commentService.saveComment(commentDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mnesaje("Guardado correctamente")
                    .object(CommentDto.builder()
                            .post(commentSave.getPost())
                            .body(commentSave.getBody())
                            .name(commentSave.getName())
                            .email(commentSave.getEmail())
                            .id(commentSave.getId())
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

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody CommentDto commentDto, @PathVariable Long id) {
        Comment commentUpdate = null;
        try {
            if (commentService.existsById(id)) {
                commentDto.setId(id);
                commentUpdate = commentService.saveComment(commentDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mnesaje("Guardado correctamente")
                        .object(CommentDto.builder()
                                .id(commentUpdate.getId())
                                .email(commentUpdate.getEmail())
                                .name(commentUpdate.getName())
                                .body(commentUpdate.getBody())
                                .post(commentUpdate.getPost())
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

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Comment commentDelete = commentService.findCommentByid(id);
            commentService.deleteComment(commentDelete);
            return new ResponseEntity<>(commentDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mnesaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Comment comment = commentService.findCommentByid(id);

        if (comment == null) {
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
                        .object(CommentDto.builder()
                                .post(comment.getPost())
                                .body(comment.getBody())
                                .name(comment.getName())
                                .email(comment.getEmail())
                                .id(comment.getId())
                                .build())
                        .build()
                , HttpStatus.OK);
    }

}


