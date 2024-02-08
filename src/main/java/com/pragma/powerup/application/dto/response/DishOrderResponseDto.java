package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.DishModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DishOrderResponseDto {

    private DishResponseDto dishModels;
    private Integer amount;

}