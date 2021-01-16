package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.AppUser;
import com.codegym.airbnb.service.apartment.IApartmentService;
import com.codegym.airbnb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/apartments")
public class ApartmentController {
    @Autowired
    private IApartmentService apartmentService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Iterable<Apartment>> getAll() {
        return new ResponseEntity<>(apartmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/seven")
    public ResponseEntity<Iterable<Apartment>> findSevenApartment() {
        return new ResponseEntity<>(apartmentService.findSevenApartment(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Apartment> createApartment(@RequestBody Apartment apartment) {
        return new ResponseEntity<>(apartmentService.save(apartment), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable Long id) {
        Optional<Apartment> apartmentOptional = apartmentService.findById(id);
        return apartmentOptional.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Apartment> updateApartment(@PathVariable Long id, @RequestBody Apartment apartment) {
//        Optional<Apartment> apartmentOptional = iApartmentService.findById(id);
//        return apartmentOptional.map(apartment1 -> {
//            apartment.setId(apartment1.getId());
//            return new ResponseEntity<>(iApartmentService.save(apartment), HttpStatus.OK);
//        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @PutMapping("/edit")
    public ResponseEntity<Apartment> updateApartment(@RequestBody Apartment apartment) {
        return new ResponseEntity<>(apartmentService.save(apartment), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Apartment> stopApartment(@PathVariable Long id) {
        Optional<Apartment> apartmentOptional = apartmentService.findById(id);
        return apartmentOptional.map(product -> {
            apartmentService.stopSelling(id);
            return new ResponseEntity<Apartment>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/repair/{id}")
    public ResponseEntity<Apartment> repairApartment(@PathVariable Long id) {
        Optional<Apartment> apartmentOptional = apartmentService.findById(id);
        return apartmentOptional.map(product -> {
            apartmentService.repairSelling(id);
            return new ResponseEntity<Apartment>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/rentagain/{id}")
    public ResponseEntity<Apartment> rentAgain(@PathVariable Long id) {
        Optional<Apartment> apartmentOptional = apartmentService.findById(id);
        return apartmentOptional.map(product -> {
            apartmentService.rentAgainApartment(id);
            return new ResponseEntity<Apartment>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Apartment>> getApartmentByUser(@PathVariable Long id) {
        AppUser appUser = userService.findById(id).get();
        return new ResponseEntity<>(apartmentService.findAllByUser(appUser), HttpStatus.OK);
    }
}
