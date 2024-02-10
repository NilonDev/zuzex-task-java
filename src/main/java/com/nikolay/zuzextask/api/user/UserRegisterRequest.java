package com.nikolay.zuzextask.api.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRegisterRequest {
    private String name;
    private Integer years;
    private String email;
    private String password;
    private String confirmPassword;

    @Override
    public String toString() {
        return "UserRegisterRequest{" +
                "name='" + name + '\'' +
                ", years=" + years +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
