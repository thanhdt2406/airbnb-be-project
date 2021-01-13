package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends CrudRepository<Long, User> {
}
