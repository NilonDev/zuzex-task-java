package com.nikolay.zuzextask.application.user;

import com.nikolay.zuzextask.api.user.UserModel;
import com.nikolay.zuzextask.api.user.UserRegisterRequest;
import com.nikolay.zuzextask.api.user.UserUpdateRequest;

public interface UserService {
    UserModel getUser(Long userId);

    UserModel registerUser(UserRegisterRequest registerRequest);

    void deleteUser(Long userId);

    UserModel updateUser(Long userId, UserUpdateRequest updateRequest);
}
