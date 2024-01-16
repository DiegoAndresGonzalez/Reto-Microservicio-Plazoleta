package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.CreateDishRequestDto;
import com.pragma.powerup.application.dto.request.UpdateDishRequestDto;

public interface IDishHandler {

    void createDish(CreateDishRequestDto createDishRequestDto);
    void updateDish(UpdateDishRequestDto updateDishRequestDto);

}