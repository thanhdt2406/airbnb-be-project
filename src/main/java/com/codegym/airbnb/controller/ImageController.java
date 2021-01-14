package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.Image;
import com.codegym.airbnb.service.image.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private IImageService imageService;


    @GetMapping
    public ResponseEntity<Iterable<Image>> getAll() {
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Image> createApartment(@RequestBody Image image) {
        return new ResponseEntity<>(imageService.save(image), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        return imageOptional.map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable Long id, @RequestBody Image image) {
        Optional<Image> imageOptional = imageService.findById(id);
        return imageOptional.map(image1 -> {
            image.setId(image1.getId());
            return new ResponseEntity<>(imageService.save(image), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Image> deleteApartment(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        return imageOptional.map(product -> {
            imageService.delete(id);
            return new ResponseEntity<Image>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
