package com.nikolay.zuzextask.application.authentication;

import com.nikolay.zuzextask.api.authentication.AuthenticationRequest;
import com.nikolay.zuzextask.api.authentication.AuthenticationResponse;
import com.nikolay.zuzextask.config.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        );
        UserDetails userDetails = (UserDetails) authenticationManager.authenticate(token).getPrincipal();
        return new AuthenticationResponse(jwtService.generateToken(userDetails));
    }
}