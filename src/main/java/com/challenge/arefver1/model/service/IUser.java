package com.challenge.arefver1.model.service;

import com.challenge.arefver1.model.dto.UserDto;
import com.challenge.arefver1.model.entities.User;

import java.util.List;

public interface IUser {

    List<User> listAlll();
    User save(UserDto user);

    User findByid(Long id);

    void delete(User User);

    boolean existsById(Long id);
}
