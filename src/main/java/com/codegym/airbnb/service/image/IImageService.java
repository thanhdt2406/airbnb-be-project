package com.codegym.airbnb.service.image;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.Image;
import com.codegym.airbnb.service.IGeneralService;

public interface IImageService extends IGeneralService<Image> {
    Iterable<Image> getAllByApartment(Apartment apartment);
}
