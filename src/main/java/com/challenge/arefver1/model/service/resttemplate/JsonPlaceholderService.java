package com.challenge.arefver1.model.service.resttemplate;

import com.challenge.arefver1.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class JsonPlaceholderService {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private final RestTemplate restTemplate;

    @Autowired
    public JsonPlaceholderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User getUserByUsername(String username) {
        String url = BASE_URL + "/users?username=" + username;
        return restTemplate.getForObject(url, User[].class)[0];
    }
}
