package com.challenge.arefver1.model.service.impl;

import com.challenge.arefver1.model.dao.UserDao;
import com.challenge.arefver1.model.dto.PostDto;
import com.challenge.arefver1.model.dto.UserDto;
import com.challenge.arefver1.model.entities.Post;
import com.challenge.arefver1.model.entities.User;
import com.challenge.arefver1.model.service.IUser;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Component
public class UserImpl implements IUser {


    private final String BASE_URL = "https://jsonplaceholder.typicode.com/users";
    private final RestTemplate restTemplate;

    @Autowired
    public UserImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<UserDto> findByUsername(String username) {
        String url = BASE_URL + "?username=" + username;
        UserDto[] users = restTemplate.getForObject(url, UserDto[].class);
        assert users != null;
        return Arrays.asList(users);
    }

    @Override
    public List<UserDto> findByEmail(String email) {
        String url = BASE_URL + "?email=" + email;
        UserDto[] users = restTemplate.getForObject(url, UserDto[].class);
        assert users != null;
        return Arrays.asList(users);
    }

    @Override
    public List<UserDto> findByCity(String city) {
        String url = BASE_URL + "?address.city=" + city;
        UserDto[] users = restTemplate.getForObject(url, UserDto[].class);
        assert users != null;
        return Arrays.asList(users);
    }

    @Override
    public int countUserPosts(Long userId) {
        String url = BASE_URL + "/posts?userId=" + userId;
        PostDto[] posts = restTemplate.getForObject(url, PostDto[].class);
        if (posts != null) {
            return posts.length;
        } else {
            return 0;
        }
    }
}