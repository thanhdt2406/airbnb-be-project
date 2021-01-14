package com.codegym.airbnb.service.image;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.Image;
import com.codegym.airbnb.repository.IApartmentRepository;
import com.codegym.airbnb.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ImageService implements IImageService {
    @Autowired
    private IImageRepository iImageRepository;

    @Override
    public Iterable<Image> findAll() {
        return iImageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return iImageRepository.findById(id);
    }

    @Override
    public Image save(Image image) {
        return iImageRepository.save(image);
    }

    @Override
    public void delete(Long id) {
        iImageRepository.deleteById(id);
    }
}
