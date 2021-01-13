package com.codegym.airbnb.service;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> getAll();

    T save(T t);

    Optional<T> findById(Long id);

    void remove(Long id);
}
