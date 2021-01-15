package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.Rent;
import com.codegym.airbnb.model.User;
import com.codegym.airbnb.service.rent.IRentService;
import com.codegym.airbnb.service.user.IUserService;
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


    @PostMapping("/{apartmentID}")
    public ResponseEntity<Rent> rentByApartmentID(@PathVariable Long apartmentID,@RequestBody Date startDate ,@RequestBody Date endDate){
        return new ResponseEntity<>(rentService.saveByApartmentID(apartmentID,startDate,endDate), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Rent>> findAllByApartmentID(@PathVariable Long apartmentID){
        return new ResponseEntity<>(rentService.findAllByApartmentID(apartmentID),HttpStatus.OK);
    }

}
