package com.challenge.arefver1.model.service.impl;

import com.challenge.arefver1.model.dao.UserDao;
import com.challenge.arefver1.model.entities.User;
import com.challenge.arefver1.model.service.IUser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component
public class UserImpl implements IUser {
    private final UserDao userDao;
    @Autowired
    public UserImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findByid(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }
}
