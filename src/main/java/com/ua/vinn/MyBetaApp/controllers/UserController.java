package com.ua.vinn.MyBetaApp.controllers;

import com.ua.vinn.MyBetaApp.config.jwt.JwtUtils;
import com.ua.vinn.MyBetaApp.dto.response.MessageResponse;
import com.ua.vinn.MyBetaApp.models.User;
import com.ua.vinn.MyBetaApp.repositories.UserRepository;
import com.ua.vinn.MyBetaApp.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user){

        User userExists = userRepository.findUserBylogin(user.getLogin());

        if (userExists != null) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("There is already a user with the login of " + user.getLogin()));
        }
        customUserDetailsService.saveUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody User user){
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        } catch(BadCredentialsException e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error during authentication " + user.getLogin()));
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(new MessageResponse(jwtUtils.generateJwtToken(authentication)));
    }


    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser(){
        return ResponseEntity.ok(new MessageResponse("You are logged out!"));
    }





}
