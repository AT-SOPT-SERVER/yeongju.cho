package org.sopt.user.api.controller;

import jakarta.validation.Valid;
import org.sopt.user.api.service.UserService;
import org.sopt.user.api.dto.UserSignupDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/signup")
    public ResponseEntity<Void> signup(
            @RequestBody @Valid final UserSignupDto userSignupDto
    ){
        long userId = userService.signup(userSignupDto);
        return ResponseEntity.created(URI.create(Long.valueOf(userId).toString())).build();
    }
}