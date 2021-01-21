package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.Rating;
import com.codegym.airbnb.model.User;
import com.codegym.airbnb.service.rating.IRatingService;
import com.codegym.airbnb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private IRatingService ratingService;

    @Autowired
    private IUserService userService;

    @GetMapping("/apartments/{id}")
    public ResponseEntity<Rating> findByApartment_Id(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ratingService.findByRent_Id(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        Long rentID = rating.getRent().getId();
        if (ratingService.isExist(rentID)){
            rating.setId(ratingService.findByRent_Id(rentID).getId());
        }
        return new ResponseEntity<>(ratingService.save(rating), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> editRating(@RequestBody Rating rating) {
        return new ResponseEntity<>(ratingService.save(rating), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Rating> deleteUser(@PathVariable Long id) {
        Optional<Rating> ratingOptional = ratingService.findById(id);
        return ratingOptional.map(rating -> {
            ratingService.delete(id);
            return new ResponseEntity<Rating>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}