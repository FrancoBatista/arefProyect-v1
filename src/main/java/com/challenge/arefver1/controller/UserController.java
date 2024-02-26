package com.challenge.arefver1.controller;

import com.challenge.arefver1.model.entities.User;
import com.challenge.arefver1.model.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUser userService;
    @Autowired
    public UserController(IUser userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User upddate(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String, Object> responseUser = new HashMap<>();
        try{
            User userDelete = userService.findByid(id);
            userService.delete(userDelete);
            return new ResponseEntity<>(userDelete,HttpStatus.NO_CONTENT);

        }catch (DataAccessException exDt) {
            responseUser.put("mensaje", exDt.getMessage());
            responseUser.put("User", null);

        }return new ResponseEntity<>(responseUser, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User showById(@PathVariable Long id){
        return userService.findByid(id);
    }
}
