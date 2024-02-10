package com.nikolay.zuzextask.application.house;

import com.nikolay.zuzextask.api.exception.ForbiddenException;
import com.nikolay.zuzextask.api.exception.ResourceAlreadyExistsException;
import com.nikolay.zuzextask.api.exception.ResourceNotFoundException;
import com.nikolay.zuzextask.api.house.HouseCreateRequest;
import com.nikolay.zuzextask.api.house.HouseModel;
import com.nikolay.zuzextask.api.house.HouseUpdateRequest;
import com.nikolay.zuzextask.domain.house.House;
import com.nikolay.zuzextask.domain.house.HouseRepository;
import com.nikolay.zuzextask.domain.user.User;
import com.nikolay.zuzextask.domain.user.UserRepository;
import com.nikolay.zuzextask.utility.mapper.house.HouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HouseServiceImp implements HouseService {
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final HouseMapper houseMapper;

    @Transactional(readOnly = true)
    @Override
    public HouseModel getHouse(Long houseId) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "House with id [%d] not found".formatted(houseId)
                ));
        return houseMapper.map(house);
    }

    @Transactional(readOnly = true)
    @Override
    public List<HouseModel> getAllHouses() {
        return houseMapper.map(houseRepository.findAll());
    }

    @Override
    public HouseModel createHouse(Long userId, HouseCreateRequest createRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id [%d] not found".formatted(userId)
                ));
        if (houseRepository.existsByLocalityAndStreetAndNumber(
                createRequest.getLocality(),
                createRequest.getStreet(),
                createRequest.getNumber()
        )) {
            throw new ResourceAlreadyExistsException("The house at this address already exists");
        }
        House house = houseMapper.map(createRequest, user);
        return houseMapper.map(houseRepository.save(house));
    }

    @Override
    public void deleteHouse(Long userId, Long houseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id [%d] not found".formatted(userId)
                ));
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "House with id [%d] not found".formatted(userId)
                ));
        if (!house.getOwner().equals(user)) {
            throw new ForbiddenException(
                    "you cannot delete a house with id = [%d] because you are not the owner".formatted(houseId)
            );
        }
        user.removeHouse(house);
        houseRepository.delete(house);
    }

    @Override
    public HouseModel updateHouse(Long userId, Long houseId, HouseUpdateRequest updateRequest) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id [%d] not found".formatted(userId)
                ));
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "House with id [%d] not found".formatted(userId)
                ));
        if (!house.getOwner().equals(owner)) {
            throw new ForbiddenException(
                    "you cannot update the house id [%d] details because you are not the owner".formatted(houseId)
            );
        }
        if (updateRequest.getLocality() != null && !updateRequest.getLocality().equals(house.getLocality())) {
            house.setLocality(updateRequest.getLocality());
        }
        if (updateRequest.getStreet() != null && !updateRequest.getStreet().equals(house.getStreet())) {
            house.setStreet(updateRequest.getStreet());
        }
        if (updateRequest.getNumber() != null && !updateRequest.getNumber().equals(house.getNumber())) {
            house.setNumber(updateRequest.getNumber());
        }
        houseRepository.save(house);
        return houseMapper.map(house);
    }

    @Override
    public void lodge(Long userId, Long houseId, Long lodgerId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id [%d] not found".formatted(userId)
                ));
        User lodger = userRepository.findById(lodgerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id [%d] not found".formatted(userId)
                ));
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "House with id [%d] not found".formatted(userId)
                ));
        if (!house.getOwner().equals(owner)) {
            throw new ForbiddenException(
                    "You cannot add a lodger id = [%d] because you are not the owner of the house id = [%d]"
                            .formatted(lodgerId, houseId)
            );
        }
        if (!house.getLodgers().contains(lodger)) {
            throw new ResourceAlreadyExistsException(
                    "Lodger id = [%d] already lives in the house with id = [%d]".formatted(lodgerId, houseId)
            );
        }
        house.addLodger(lodger);
    }

    @Override
    public void evict(Long userId, Long houseId, Long lodgerId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id [%d] not found".formatted(userId)
                ));
        User lodger = userRepository.findById(lodgerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id [%d] not found".formatted(userId)
                ));
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "House with id [%d] not found".formatted(userId)
                ));
        if (!house.getOwner().equals(owner)) {
            throw new ForbiddenException(
                    "You cannot add a lodger id = [%d] because you are not the owner of the house id = [%d]"
                            .formatted(lodgerId, houseId)
            );
        }
        if (!house.getLodgers().contains(lodger)) {
            throw new ResourceNotFoundException(
                    "There is no lodger with id = [%d] in the house with number = [%d]".formatted(lodgerId, houseId)
            );
        }
        house.getLodgers().remove(lodger);
    }
}
