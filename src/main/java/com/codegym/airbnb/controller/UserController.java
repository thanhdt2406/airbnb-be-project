package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.User;
import com.codegym.airbnb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserInformation(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(data -> {
            user.setId(data.getId());
            if (user.getName().equalsIgnoreCase("")) {
                user.setName(data.getName());
            }
            user.setPassword(data.getPassword());
            user.setUsername(data.getUsername());
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> productOptional = userService.findById(id);
        return productOptional.map(product -> {
            userService.delete(id);
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/passwords")
    public ResponseEntity<User> changePassword(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (!bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(userService.edit(user), HttpStatus.OK);
        }
        return null;
    }

}
