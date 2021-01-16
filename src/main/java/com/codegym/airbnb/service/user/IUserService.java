package com.codegym.airbnb.service.user;

import com.codegym.airbnb.model.User;
import com.codegym.airbnb.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);

    User findByEmail(String emial);

    User changePassword(Long id, String password);

    User getCurrentUser();

    User edit(User user);

    User saveSocialUser(User user);
}
