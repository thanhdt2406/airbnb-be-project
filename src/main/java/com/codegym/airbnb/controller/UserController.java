package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.User;
import com.codegym.airbnb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserInformation(@PathVariable Long id, @RequestBody User user) {
        Optional<User> productOptional = userService.findById(id);
        return productOptional.map(product1 -> {
            user.setId(product1.getId());
            if (user.getName().equalsIgnoreCase("")) {
                user.setName(product1.getName());
            }
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

//    @GetMapping("current-user")
//    public ResponseEntity<User> getCurrentUser() {
//        return new ResponseEntity<>(userService.findByUsername(getPrincipal()), HttpStatus.OK);
//    }
//
//    private String getPrincipal() {
//        String userName = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            userName = ((UserDetails) principal).getUsername();
//        } else {
//            userName = principal.toString();
//        }
//        return userName;
//    }
}
