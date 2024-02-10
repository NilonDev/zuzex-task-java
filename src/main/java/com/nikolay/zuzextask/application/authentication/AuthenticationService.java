package com.nikolay.zuzextask.application.authentication;

import com.nikolay.zuzextask.api.authentication.AuthenticationRequest;
import com.nikolay.zuzextask.api.authentication.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
