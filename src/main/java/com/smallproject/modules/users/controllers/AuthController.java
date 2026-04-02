package com.smallproject.modules.users.controllers;

import com.smallproject.modules.users.dtos.requestDto.LoginRequest;
import com.smallproject.modules.users.dtos.responseDto.LoginResponse;
import com.smallproject.modules.users.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(
            UserService userService
    ){
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginRequest request){
        Object result = userService.authenticate(request);

        if (result instanceof LoginResponse loginResponse){
            return ResponseEntity.ok(loginResponse);
        }

        if (result instanceof ErrorResponse errorResponse){
            return ResponseEntity.unprocessableEntity().body(errorResponse);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("network error");
    }

}
