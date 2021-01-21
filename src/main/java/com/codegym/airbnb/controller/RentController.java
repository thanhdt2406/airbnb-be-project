package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.Rent;
import com.codegym.airbnb.model.TotalIncome;
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
        Iterable<Rent> rents = rentService.getAllBookingApartmentByUser(id);
        return new ResponseEntity<>(rents,HttpStatus.OK);
    }

    @GetMapping("rented/{userId}")
    public ResponseEntity<Iterable<Rent>> getAllRented(@PathVariable Long userId){
        return new ResponseEntity<>(rentService.getAllRented(userId),HttpStatus.OK);
    }

    @GetMapping("/list/{apartmentId}")
    public ResponseEntity<Iterable<Rent>> getAllRentedByApartment(@PathVariable Long apartmentId) {
        return new ResponseEntity<>(rentService.getAllRentedByApartment(apartmentId),HttpStatus.OK);
    }

    @GetMapping("/money/user/{id}/years/{year}/months/{month}")
    public ResponseEntity<Long>getTotalIncomeByUserId(@PathVariable Long id,@PathVariable int year,@PathVariable int month) {
        Long totalIncomes = rentService.getTotalIncomeByUserId(id,year,month);
        return new ResponseEntity<>(totalIncomes, HttpStatus.OK);
    }
}
