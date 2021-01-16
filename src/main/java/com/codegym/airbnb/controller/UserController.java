package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.AppUser;
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
    public ResponseEntity<Iterable<AppUser>> getAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserByID(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUserInformation(@PathVariable Long id, @RequestBody AppUser appUser) {
        Optional<AppUser> productOptional = userService.findById(id);
        return productOptional.map(product1 -> {
            appUser.setId(product1.getId());
            if (appUser.getName().equalsIgnoreCase("")) {
                appUser.setName(product1.getName());
            }
            return new ResponseEntity<>(userService.save(appUser), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppUser> deleteUser(@PathVariable Long id) {
        Optional<AppUser> productOptional = userService.findById(id);
        return productOptional.map(product -> {
            userService.delete(id);
            return new ResponseEntity<AppUser>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/passwords")
    public ResponseEntity<AppUser> changePassword(@Valid @RequestBody AppUser appUser, BindingResult bindingResult) {
        if (!bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(userService.save(appUser), HttpStatus.OK);
        }
        return null;
    }

}
