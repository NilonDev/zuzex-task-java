package com.nikolay.zuzextask.utility.mapper.user;

import com.nikolay.zuzextask.api.user.UserModel;
import com.nikolay.zuzextask.api.user.UserRegisterRequest;
import com.nikolay.zuzextask.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PasswordEncoderShell.class)
public interface UserMapper {

    @Mapping(target = "password", qualifiedBy = {Encoder.class, PlainEncode.class})
    User map(UserRegisterRequest registerRequest);
    UserModel map(User user);
}
