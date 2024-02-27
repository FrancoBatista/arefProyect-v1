package com.challenge.arefver1.model.service.impl;

import com.challenge.arefver1.model.dao.UserDao;
import com.challenge.arefver1.model.dto.UserDto;
import com.challenge.arefver1.model.entities.User;
import com.challenge.arefver1.model.service.IUser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserImpl implements IUser {
    private final UserDao userDao;
    @Autowired
    public UserImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> listAlll() {
        return (List<User>) userDao.findAll();
    }

    @Transactional
    @Override
    public User save(UserDto userDto) {
        User user = User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .userName(userDto.getUserName())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .website(userDto.getWebsite())
                .company(userDto.getCompany())
                .build();
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

    @Override
    public boolean existsById(Long id) {
        return userDao.existsById(id);
    }
}
