package com.codegym.airbnb.service.user;

import com.codegym.airbnb.model.AppUser;

import com.codegym.airbnb.model.UserPrinciple;
import com.codegym.airbnb.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        appUser.setPassword(encoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrinciple(appUser);
    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public AppUser findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public AppUser changePassword(Long id, String password) {
        AppUser appUser = findById(id).get();
        appUser.setPassword(encoder.encode(password));
        if (!appUser.getPassword().equals(getCurrentUser().getPassword())) {
            return null;
        }
        return save(appUser);
    }

    @Override
    public AppUser getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByUsername(userDetails.getUsername());
    }
}
