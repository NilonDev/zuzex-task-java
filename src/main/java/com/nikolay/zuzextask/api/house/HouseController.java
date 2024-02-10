package com.nikolay.zuzextask.api.house;

import com.nikolay.zuzextask.application.house.HouseService;
import com.nikolay.zuzextask.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/houses")
public class HouseController {
    private final HouseService houseService;

    @GetMapping("/{id}")
    public HouseModel getHouse(Long id) {
        return houseService.getHouse(id);
    }

    @GetMapping
    public List<HouseModel> getAllHouses() {
        return houseService.getAllHouses();
    }

    @PostMapping
    public HouseModel createHouse(
            @AuthenticationPrincipal User user,
            @RequestBody HouseCreateRequest createRequest
    ) {
        return houseService.createHouse(user.getId(), createRequest);
    }

    @PutMapping("/{houseId}")
    public HouseModel updateHouse(
            @AuthenticationPrincipal User user,
            @RequestBody HouseUpdateRequest updateRequest,
            @PathVariable(name = "houseId") Long houseId
    ) {
        return houseService.updateHouse(user.getId(), houseId, updateRequest);
    }

    @DeleteMapping("/houseId")
    public void deleteHouse(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "houseId") Long houseId) {
        houseService.deleteHouse(user.getId(), houseId);
    }

    @PostMapping("/{houseId}/lodgers/{lodgerId}")
    public void lodgeUser(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "houseId") Long houseId,
            @PathVariable(name = "lodgerId") Long lodgerId
    ) {
        houseService.lodge(user.getId(), houseId, lodgerId);
    }

    @DeleteMapping("/{houseId}/lodgers/{lodgerId}")
    public void evictUser(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "houseId") Long houseId,
            @PathVariable(name = "lodgerId") Long lodgerId
    ) {
        houseService.evict(user.getId(), houseId, lodgerId);
    }
}
