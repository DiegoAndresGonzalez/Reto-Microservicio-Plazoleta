package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.CreateDishRequestDto;
import com.pragma.powerup.application.dto.request.UpdateDishRequestDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;

    @Override
    public void createDish(CreateDishRequestDto createDishRequestDto) {
        DishModel dishModel = dishRequestMapper.toCreateDishModel(createDishRequestDto);
        dishServicePort.createDish(dishModel);
    }

    @Override
    public void updateDish(UpdateDishRequestDto updateDishRequestDto) {
        DishModel dishModel = dishRequestMapper.toUpdateDishModel(updateDishRequestDto);
        dishServicePort.updateDish(dishModel);
    }
}