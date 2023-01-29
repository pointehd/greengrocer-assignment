package com.assignment.greengrocer.greengrocer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PriceResponse {

    private String name;
    private Long price;

}
