package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<AppUser,Long> {
    AppUser findByUsername(String username);

    AppUser findByEmail(String email);
}
