package com.nikolay.zuzextask.api.authentication;

import com.nikolay.zuzextask.application.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authRequest) {
        return authenticationService.authenticate(authRequest);
    }
}
