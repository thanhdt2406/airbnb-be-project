package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.Rent;
import com.codegym.airbnb.service.rent.IRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

}
