package com.challenge.arefver1.model.service;

import com.challenge.arefver1.model.dto.UserDto;
import com.challenge.arefver1.model.entities.Post;
import com.challenge.arefver1.model.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUser {
    List<UserDto> findByUsername(String username);

    List<UserDto> findByEmail(String email);

    List<UserDto> findByCity(String city);

    int countUserPosts(Long userId);



}
