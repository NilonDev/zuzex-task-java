package com.nikolay.zuzextask.application.house;

import com.nikolay.zuzextask.api.house.HouseCreateRequest;
import com.nikolay.zuzextask.api.house.HouseModel;
import com.nikolay.zuzextask.api.house.HouseUpdateRequest;

import java.util.List;

public interface HouseService {
    HouseModel createHouse(Long userId, HouseCreateRequest createRequest);
    void deleteHouse(Long userId, Long houseId);
    HouseModel getHouse(Long houseId);
    List<HouseModel> getAllHouses();
    HouseModel updateHouse(Long userId, Long houseId, HouseUpdateRequest updateRequest);
    void lodge(Long userId, Long houseId, Long lodgerId);
    void evict(Long userId, Long houseId, Long lodgerId);
}
