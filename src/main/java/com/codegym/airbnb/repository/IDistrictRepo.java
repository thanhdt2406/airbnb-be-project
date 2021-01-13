package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.District;
import org.springframework.data.repository.CrudRepository;

public interface IDistrictRepo extends CrudRepository<District, Long> {
}
