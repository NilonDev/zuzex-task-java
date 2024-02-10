package com.nikolay.zuzextask.utility.mapper.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Encoder
@Component
@RequiredArgsConstructor
public class PasswordEncoderShell {
    private final PasswordEncoder passwordEncoder;

    @PlainEncode
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
