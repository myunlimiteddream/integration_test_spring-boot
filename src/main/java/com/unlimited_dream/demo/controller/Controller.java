package com.unlimited_dream.demo.controller;

import com.unlimited_dream.demo.dto.UserDTO;
import com.unlimited_dream.demo.entity.User;
import com.unlimited_dream.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class Controller {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO UserDTO) {
        return new ResponseEntity<User>(userService.createUser(UserDTO), HttpStatus.OK);
    }
}