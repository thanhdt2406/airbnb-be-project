package com.codegym.airbnb.service.rating;

import com.codegym.airbnb.model.Rating;
import com.codegym.airbnb.service.IGeneralService;

public interface IRatingService extends IGeneralService<Rating> {
    Rating findByRent_Id(Long id);

    boolean isExist(Long rentID);
}
