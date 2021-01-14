package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.Province;
import com.codegym.airbnb.service.province.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<Iterable<Province>> getAllProvince(){
        return new ResponseEntity<>(provinceService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Province> getApartmentById(@PathVariable Long id) {
        Optional<Province> provinceOptional = provinceService.findById(id);
        return provinceOptional.map(province -> new ResponseEntity<>(province, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
