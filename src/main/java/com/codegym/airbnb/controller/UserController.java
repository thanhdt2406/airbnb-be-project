package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.User;
import com.codegym.airbnb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;


    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> productOptional = userService.findById(id);
        return productOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
