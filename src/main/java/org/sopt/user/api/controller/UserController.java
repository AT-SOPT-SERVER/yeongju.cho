package org.sopt.user.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.auth.core.service.AuthService;
import org.sopt.auth.api.dto.JwtTokenDto;
import org.sopt.user.api.service.UserService;
import org.sopt.user.api.dto.UserSignupDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/users/signup")
    public ResponseEntity<Void> signup(
            @RequestBody @Valid final UserSignupDto userSignupDto
    ) {
        long userId = userService.signup(userSignupDto);
        return ResponseEntity.created(URI.create(Long.valueOf(userId).toString())).build();
    }

    @PostMapping("/users/login")
    public ResponseEntity<JwtTokenDto> login(
            @RequestParam final Long userId
    ){
        return ResponseEntity.ok(authService.login(userId));
    }
}