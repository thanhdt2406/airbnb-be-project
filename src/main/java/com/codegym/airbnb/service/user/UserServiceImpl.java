package com.codegym.airbnb.service.user;

import com.codegym.airbnb.model.User;

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
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User saveSocialUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrinciple(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        if (userRepository.findByEmail(email) != null) {
            return userRepository.findByEmail(email);
        } else {
            User newUser = new User(email);
            newUser.setPassword("123456");
            save(newUser);
            return newUser;
        }
    }

    @Override
    public User changePassword(Long id, String password) {
        User user = findById(id).get();
        user.setPassword(encoder.encode(password));
        if (!user.getPassword().equals(getCurrentUser().getPassword())) {
            return null;
        }
        return save(user);
    }

    @Override
    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByUsername(userDetails.getUsername());
    }

    public User edit(User user) {
        return userRepository.save(user);
    }
}
