package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDishStatusDto {

    private Long id;
    private Boolean active;

}