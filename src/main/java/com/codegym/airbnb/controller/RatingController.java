package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.Rating;
import com.codegym.airbnb.service.rating.IRatingService;
import com.codegym.airbnb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private IRatingService ratingService;

    @Autowired
    private IUserService userService;

    @GetMapping("/apartments/{id}")
    public ResponseEntity<Iterable<Rating>> findByApartment_Id(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ratingService.findByApartment_Id(id), HttpStatus.OK);
    }

    @PostMapping("/apartments")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        return new ResponseEntity<>(ratingService.save(rating), HttpStatus.OK);
    }

    @PutMapping("/apartments")
    public ResponseEntity<Rating> editRating(@RequestBody Rating rating) {
        return new ResponseEntity<>(ratingService.save(rating), HttpStatus.OK);
    }

//    @GetMapping("apartments/{apartmentId}/users/{userId}")
//    public ResponseEntity<Rating> findByApartment_IdAnd
}