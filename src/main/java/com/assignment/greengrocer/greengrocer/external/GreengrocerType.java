package com.assignment.greengrocer.greengrocer.external;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GreengrocerType {

    FRUIT("fruit"),
    VEGETABLE("vegetable");

    @Getter
    private final String value;

    public static GreengrocerType of(String value) {
        value = value.toLowerCase();

        for (GreengrocerType type : GreengrocerType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("일치하는 은행 코드가 없습니다.");
    }
}
