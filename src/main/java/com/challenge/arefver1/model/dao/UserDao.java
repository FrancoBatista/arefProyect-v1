package com.challenge.arefver1.model.dao;

import com.challenge.arefver1.model.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


public interface UserDao extends CrudRepository<User, Long> {
}
