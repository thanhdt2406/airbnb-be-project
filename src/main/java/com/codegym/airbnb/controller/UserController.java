package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.User;
import com.codegym.airbnb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/edit/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

}
