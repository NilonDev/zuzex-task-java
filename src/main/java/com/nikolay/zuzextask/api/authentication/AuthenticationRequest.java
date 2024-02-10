package com.nikolay.zuzextask.api.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {
    private String email;
    private String password;
}
