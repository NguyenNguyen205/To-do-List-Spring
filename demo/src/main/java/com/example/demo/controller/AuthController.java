package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.record.AuthRequest;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<String> login(@RequestBody @Valid AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User userAccount = (User) authentication.getPrincipal();
        return new ResponseEntity<>(userAccount.getName(), HttpStatus.OK);


    }
    @PostMapping(value = "/signup", produces = "application/json")
    public ResponseEntity<String> signup(@RequestBody @Valid AuthRequest authRequest) {
        if (userRepository.existsByName(authRequest.username())) {
            return new ResponseEntity<>("User already existe", HttpStatus.BAD_REQUEST);
        }

        User userEntity = new User();
        userEntity.setName(authRequest.username());
        userEntity.setPassword(passwordEncoder.encode(authRequest.password()));

        Role roles = roleRepository.findByName("USER").get();
        userEntity.setRoles(Collections.singletonList(roles));
        userRepository.save(userEntity);
        return new ResponseEntity<>("User created successfully", HttpStatus.OK);

    }
}

