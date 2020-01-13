package com.example.feeder.application.controller;

import com.example.feeder.application.service.authService.LoginService;
import com.example.feeder.application.service.authService.RegisterService;
import com.example.feeder.application.request.LoginRequest;
import com.example.feeder.application.request.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final LoginService loginService;
    private final RegisterService registerService;

    public AuthController(LoginService loginService, RegisterService registerService) {
        this.loginService = loginService;
        this.registerService = registerService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(loginService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return  new ResponseEntity<>(registerService.registerUser(registerRequest), HttpStatus.OK);
    }
}
