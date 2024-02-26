package com.challenge.arefver1.model.service;

import com.challenge.arefver1.model.entities.User;

public interface IUser {
    User save(User user);

    User findByid(Long id);

    void delete(User User);
}
