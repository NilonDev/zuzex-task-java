package com.nikolay.zuzextask.api.house;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HouseModel {
    private String locality;
    private String street;
    private String number;

    @Override
    public String toString() {
        return "HouseModel{" +
                "locality='" + locality + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
