package com.challenge.arefver1.controller;

import com.challenge.arefver1.model.dto.UserDto;
import com.challenge.arefver1.model.entities.User;
import com.challenge.arefver1.model.payload.MensajeResponse;
import com.challenge.arefver1.model.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private final IUser userService;
    @Autowired
    public UserController(IUser userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> showAll() {
        List<User> getList = userService.listAlll();
        if (getList == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mnesaje("No hay registros")
                            .object(null)
                            .build()
                    , HttpStatus.OK);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mnesaje("")
                        .object(getList)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        User userSave = null;
        try {
            userSave = userService.save(userDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mnesaje("Guardado correctamente")
                    .object(userSave.builder()
                            .id(userSave.getId())
                            .name(userSave.getName())
                            .phone(userSave.getPhone())
                            .website(userSave.getWebsite())
                            .phone(userSave.getPhone())
                            .userName(userSave.getUserName())
                            .company(userSave.getCompany())
                            .address(userSave.getAddress())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mnesaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserDto userDto, @PathVariable Long id) {
        User userUpdate = null;
        try {
            if (userService.existsById(id)) {
                userDto.setId(id);
                userUpdate = userService.save(userDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mnesaje("Guardado correctamente")
                        .object(UserDto.builder()
                                .id(userUpdate.getId())
                                .name(userUpdate.getName())
                                .phone(userUpdate.getPhone())
                                .website(userUpdate.getWebsite())
                                .phone(userUpdate.getPhone())
                                .userName(userUpdate.getUserName())
                                .company(userUpdate.getCompany())
                                .address(userUpdate.getAddress())
                                .email(userUpdate.getEmail())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mnesaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mnesaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            User userDelete = userService.findByid(id);
            userService.delete(userDelete);
            return new ResponseEntity<>(userDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mnesaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        User user = userService.findByid(id);

        if (user == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mnesaje("El registro que intenta buscar, no existe!!")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mnesaje("")
                        .object(UserDto.builder()
                                .id(user.getId())
                                .name(user.getName())
                                .phone(user.getPhone())
                                .website(user.getWebsite())
                                .address(user.getAddress())
                                .email(user.getEmail())
                                .company(user.getCompany())
                                .userName(user.getUserName())


                                .build())
                        .build()
                , HttpStatus.OK);
    }

}
