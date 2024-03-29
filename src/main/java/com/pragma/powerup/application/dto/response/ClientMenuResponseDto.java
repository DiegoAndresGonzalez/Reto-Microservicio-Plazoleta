package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientMenuResponseDto {
    private String name;
    private String description;
    private String price;
    private String imageUrl;
    private Boolean active;
}