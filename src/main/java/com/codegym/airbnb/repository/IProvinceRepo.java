package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Province;
import org.springframework.data.repository.CrudRepository;

public interface IProvinceRepo extends CrudRepository<Province, Long> {
}
