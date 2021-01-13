package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Ward;
import org.springframework.data.repository.CrudRepository;

public interface IWardRepo extends CrudRepository<Ward, Long> {
}
