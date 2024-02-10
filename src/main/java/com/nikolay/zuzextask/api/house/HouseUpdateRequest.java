package com.nikolay.zuzextask.api.house;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HouseUpdateRequest {
    private String locality;
    private String street;
    private String number;
}
