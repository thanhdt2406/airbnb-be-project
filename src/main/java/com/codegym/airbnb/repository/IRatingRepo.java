package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRatingRepo extends JpaRepository<Rating, Long> {
}
