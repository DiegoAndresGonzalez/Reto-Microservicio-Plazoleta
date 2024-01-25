package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.CreateDishRequestDto;
import com.pragma.powerup.application.dto.request.UpdateDishStatusDto;
import com.pragma.powerup.application.dto.request.UpdateDishRequestDto;
import com.pragma.powerup.application.dto.response.ClientMenuResponseDto;
import org.springframework.data.domain.Page;

public interface IDishHandler {

    void createDish(CreateDishRequestDto createDishRequestDto);
    void updateDish(UpdateDishRequestDto updateDishRequestDto);
    void updateDishStatus(UpdateDishStatusDto updateDishStatusDto);
    Page<ClientMenuResponseDto> getAllDishesPaginated(String category, Integer page, Integer size);

}