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
    private IUserRepository IUserRepository;

    @Override
    public Iterable<User> findAll() {
        return IUserRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return IUserRepository.findById(id);
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return IUserRepository.save(user);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = IUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrinciple(user);
    }

    @Override
    public User findByUsername(String username) {
        return IUserRepository.findByUsername(username);
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
        return IUserRepository.save(user);
    }
}
