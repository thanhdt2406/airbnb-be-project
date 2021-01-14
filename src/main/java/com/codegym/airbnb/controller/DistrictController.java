package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.District;
import com.codegym.airbnb.model.Province;
import com.codegym.airbnb.service.district.DistrictService;
import com.codegym.airbnb.service.province.ProvinceService;
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
    private DistrictService districtService;

    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<Iterable<District>> getAllProvince(){
        return new ResponseEntity<>(districtService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<District> getApartmentById(@PathVariable Long id) {
        Optional<District> districtOptional = districtService.findById(id);
        return districtOptional.map(district -> new ResponseEntity<>(district, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/province/{id}")
    public ResponseEntity<Iterable<District>> getAllDistrictByProvince(@PathVariable Long id) {
        Province province = provinceService.findById(id).get();
        return new ResponseEntity<>(districtService.findAllByProvince(province), HttpStatus.OK);
    }
}
