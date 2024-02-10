package com.nikolay.zuzextask.application.user;

import com.nikolay.zuzextask.api.exception.BadRequestException;
import com.nikolay.zuzextask.api.exception.ResourceAlreadyExistsException;
import com.nikolay.zuzextask.api.exception.ResourceNotFoundException;
import com.nikolay.zuzextask.api.user.UserModel;
import com.nikolay.zuzextask.api.user.UserRegisterRequest;
import com.nikolay.zuzextask.api.user.UserUpdateRequest;
import com.nikolay.zuzextask.domain.user.User;
import com.nikolay.zuzextask.domain.user.UserRepository;
import com.nikolay.zuzextask.utility.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public UserModel getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id [%d] not found".formatted(id)
                ));
        return userMapper.map(user);
    }

    @Override
    public UserModel registerUser(UserRegisterRequest registerRequest) {
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new BadRequestException(
                    "Password mismatch"
            );
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ResourceAlreadyExistsException(
                    "User with email [%s] already exists".formatted(registerRequest.getEmail())
            );
        }
        User user = userMapper.map(registerRequest);
        return userMapper.map(userRepository.save(user));
    }

    @Override
    public UserModel updateUser(Long userId, UserUpdateRequest updateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id [%d] not found".formatted(userId)
                ));

        if (updateRequest.getName() != null && !updateRequest.getName().equals(user.getName())) {
            user.setName(updateRequest.getName());
        }
        if (updateRequest.getYears() != null && !updateRequest.getYears().equals(user.getYears())) {
            user.setYears(updateRequest.getYears());
        }
        if (updateRequest.getPassword() != null && !updateRequest.getPassword().equals(user.getPassword())) {
            user.setPassword(updateRequest.getPassword());
        }
        return userMapper.map(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id [%d] not found".formatted(userId)
                ));
        userRepository.delete(user);
    }
}
