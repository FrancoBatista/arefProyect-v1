package com.challenge.arefver1.model.dao;

import com.challenge.arefver1.model.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
}
