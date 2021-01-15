package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.District;
import com.codegym.airbnb.model.Province;
import com.codegym.airbnb.service.district.DistrictServiceImpl;
import com.codegym.airbnb.service.province.ProvinceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/districts")
public class DistrictController {
    @Autowired
    private DistrictServiceImpl districtServiceImpl;

    @Autowired
    private ProvinceServiceImpl provinceServiceImpl;

    @GetMapping
    public ResponseEntity<Iterable<District>> getAllProvince(){
        return new ResponseEntity<>(districtServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<District> getApartmentById(@PathVariable Long id) {
        Optional<District> districtOptional = districtServiceImpl.findById(id);
        return districtOptional.map(district -> new ResponseEntity<>(district, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/province/{id}")
    public ResponseEntity<Iterable<District>> getAllDistrictByProvince(@PathVariable Long id) {
        Province province = provinceServiceImpl.findById(id).get();
        return new ResponseEntity<>(districtServiceImpl.findAllByProvince(province), HttpStatus.OK);
    }
}
