package com.codegym.airbnb.service.ward;

import com.codegym.airbnb.model.District;
import com.codegym.airbnb.model.Ward;
import com.codegym.airbnb.service.IGeneralService;

public interface IWardService extends IGeneralService<Ward> {
    Iterable<Ward> findAllByDistrict(District district);

    Iterable<Ward> findByDistrict_Id(Long id);

    Iterable<Ward> findByDistrict_ProvinceId(Long id);
}
