package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDishRequestDto {
    private Long id;
    private String price;
    private String description;

}