package com.codegym.airbnb.service.user;

import com.codegym.airbnb.model.User;

import com.codegym.airbnb.model.UserPrinciple;
import com.codegym.airbnb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;
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

//    @Override
//    public boolean editUser(User user) {
//        userRepository.save(user);
//        return false;
//    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
