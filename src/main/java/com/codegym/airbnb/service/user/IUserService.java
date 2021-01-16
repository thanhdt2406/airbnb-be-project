package com.codegym.airbnb.service.user;

import com.codegym.airbnb.model.AppUser;
import com.codegym.airbnb.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<AppUser>, UserDetailsService {
    AppUser findByUsername(String username);

    AppUser findByEmail(String emial);

    AppUser changePassword(Long id, String password);

    AppUser getCurrentUser();
}
