package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.ClientRestaurantResponseDto;
import org.springframework.data.domain.Page;

public interface IRestaurantHandler {
    void createRestaurant(RestaurantRequestDto restaurantRequestDto);
    Page<ClientRestaurantResponseDto> getAllRestaurantsPaginated(Integer page, Integer size);
}