package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.JwtResponse;
import com.codegym.airbnb.model.User;
import com.codegym.airbnb.service.JwtService;
import com.codegym.airbnb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {

        // Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = jwtService.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(currentUser.getId(),currentUser.getUsername(), currentUser.getPassword(),jwt, currentUser.getName(), currentUser.getAvatar(), currentUser.getPhoneNumber(), currentUser.getAddress(), currentUser.getEmail()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> createNewUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if(!bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        }
        return null;
    }
}
