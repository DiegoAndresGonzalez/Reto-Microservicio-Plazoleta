package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequestDto {

    private String name;
    private Integer nit;
    private String address;
    private String phone;
    private String urlLogo;
    private Long idProprietary;

}