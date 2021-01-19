package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.Rent;
import com.codegym.airbnb.service.rent.IRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/rents")
public class RentController {
    @Autowired
    private IRentService rentService;


    @PostMapping()
    public ResponseEntity<Rent> rentByApartmentID(@RequestBody Rent rent){
        return new ResponseEntity<>(rentService.save(rent), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Rent>> findAllByApartmentID(@PathVariable Long id){
        return new ResponseEntity<>(rentService.findAllByApartmentID(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id1}/{id2}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id1,@PathVariable Long id2) {
        rentService.cancelBooking(id1,id2);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Rent>> findAllBookingApartmentByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(rentService.getAllBookingApartmentByUser(id),HttpStatus.OK);
    }

    @GetMapping("rented/{userId}")
    public ResponseEntity<Iterable<Rent>> getAllRented(@PathVariable Long userId){
        return new ResponseEntity<>(rentService.getAllRented(userId),HttpStatus.OK);
    }

    @GetMapping("apartments/{apartmentId}/users/{userId}")
    public ResponseEntity<Rent> getBookingApartmentByUserIdAndApartment(@PathVariable("apartmentId") Long apartmentId, @PathVariable("userId") Long userId){
        return new ResponseEntity<>(rentService.getBookingApartmentByUserIdAndApartment(userId,apartmentId).get(),HttpStatus.OK);
    }
}
