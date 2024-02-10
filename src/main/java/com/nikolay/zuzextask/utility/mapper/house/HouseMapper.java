package com.nikolay.zuzextask.utility.mapper.house;

import com.nikolay.zuzextask.api.house.HouseCreateRequest;
import com.nikolay.zuzextask.api.house.HouseModel;
import com.nikolay.zuzextask.api.user.UserModel;
import com.nikolay.zuzextask.domain.house.House;
import com.nikolay.zuzextask.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HouseMapper {
    @Mapping(target = "owner", source = "user")
    House map(HouseCreateRequest createRequest, User user);
    HouseModel map(House house);
    UserModel map(User user);
    List<HouseModel> map(List<House> houses);
}
