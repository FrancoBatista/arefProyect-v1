package com.challenge.arefver1.controller;

import com.challenge.arefver1.model.dto.UserDto;
import com.challenge.arefver1.model.service.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;





    @RestController
    @RequestMapping("/users")
    public class UserController {

        private final UserImpl userService;

        @Autowired
        public UserController(UserImpl userService) {
            this.userService = userService;
        }

        // Endpoint para obtener detalles de un usuario por su nombre de usuario
        @GetMapping("/username/{username}")
        public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
            List<UserDto> users = userService.findByUsername(username);
            if (users.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(users);
        }


        // Endpoint para obtener detalles de un usuario por su email
        @GetMapping("/email/{email}")
        public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
            List<UserDto> users = userService.findByEmail(email);
            if (users.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(users);
        }

        // Endpoint para obtener detalles de un usuario por su ciudad
        @GetMapping("/city/{city}")
        public ResponseEntity<?> getUserByCity(@PathVariable String city) {
            List<UserDto> users = userService.findByCity(city);
            if (users.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(users);
        }

        // Endpoint para obtener la cantidad de artículos publicados por un usuario
        @GetMapping("/{id}/article-count")
        public ResponseEntity<Integer> getUserArticleCount(@PathVariable Long userId) {
            int articleCount = userService.countUserPosts(userId);
            return ResponseEntity.ok(articleCount);
        }
    }





