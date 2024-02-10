package com.nikolay.zuzextask.api.user;

import com.nikolay.zuzextask.application.user.UserService;
import com.nikolay.zuzextask.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/{id}")
    UserModel getUser(@PathVariable(name = "id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/register")
    UserModel registerUser(@RequestBody UserRegisterRequest registerRequest) {
        return userService.registerUser(registerRequest);
    }

    @PutMapping("/users")
    public UserModel updateUser(
            @AuthenticationPrincipal User currentUser,
            @RequestBody UserUpdateRequest updateRequest
    ) {
        return userService.updateUser(currentUser.getId(), updateRequest);
    }

    @DeleteMapping("/users")
    void deleteUser(@AuthenticationPrincipal User currentUser) {
        userService.deleteUser(currentUser.getId());
    }
}
