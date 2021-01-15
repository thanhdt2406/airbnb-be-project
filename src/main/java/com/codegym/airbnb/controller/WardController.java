package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.District;
import com.codegym.airbnb.model.Ward;
import com.codegym.airbnb.service.district.DistrictServiceImpl;
import com.codegym.airbnb.service.ward.WardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/wards")
public class WardController {
    @Autowired
    private WardServiceImpl wardServiceImpl;

    @Autowired
    private DistrictServiceImpl districtServiceImpl;

    @GetMapping
    public ResponseEntity<Iterable<Ward>> getAllWards(){
        return new ResponseEntity<>(wardServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ward> getApartmentById(@PathVariable Long id) {
        Optional<Ward> wardOptional = wardServiceImpl.findById(id);
        return wardOptional.map(ward -> new ResponseEntity<>(ward, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/districts/{id}")
    public ResponseEntity<Iterable<Ward>> getAllWardsByDistricts(@PathVariable Long id){
        District district = districtServiceImpl.findById(id).get();
        return new ResponseEntity<>(wardServiceImpl.findAllByDistrict(district), HttpStatus.OK);
    }
}
