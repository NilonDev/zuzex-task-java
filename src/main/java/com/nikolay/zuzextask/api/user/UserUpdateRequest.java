package com.nikolay.zuzextask.api.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserUpdateRequest {
    private String name;
    private Integer years;
    private String email;
    private String password;
}
