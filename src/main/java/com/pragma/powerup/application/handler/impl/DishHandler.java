package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.CreateDishRequestDto;
import com.pragma.powerup.application.dto.request.UpdateDishStatusDto;
import com.pragma.powerup.application.dto.request.UpdateDishRequestDto;
import com.pragma.powerup.application.dto.response.ClientMenuResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.application.mapper.IDishResponseMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

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

    @Override
    public void updateDishStatus(UpdateDishStatusDto updateDishStatusDto) {
        DishModel dishModel = dishRequestMapper.toModifyDishStatus(updateDishStatusDto);
        dishServicePort.updateDishStatus(dishModel);
    }

    @Override
    public Page<ClientMenuResponseDto> getAllDishesPaginated(String category, Integer page, Integer size) {
        Page<DishModel> dishModel = dishServicePort.getAllDishesPaginated(category,page,size);
        return dishModel.map(dishResponseMapper::toClientMenuResponse);
    }
}