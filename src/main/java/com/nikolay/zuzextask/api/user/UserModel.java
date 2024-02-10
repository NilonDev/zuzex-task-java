package com.nikolay.zuzextask.api.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserModel {
    private String name;
    private Integer years;
    private String email;

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", years=" + years +
                ", email='" + email + '\'' +
                '}';
    }
}
