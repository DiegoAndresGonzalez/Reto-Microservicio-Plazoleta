package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.ClientRestaurantResponseDto;
import com.pragma.powerup.application.dto.response.EmployeeResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.domain.utils.OrderStatus;
import org.springframework.data.domain.Page;

public interface IRestaurantHandler {
    void createRestaurant(RestaurantRequestDto restaurantRequestDto);
    RestaurantResponseDto findRestaurantByName(String name);
    Page<ClientRestaurantResponseDto> getAllRestaurantsPaginated(Integer page, Integer size);
    void saveEmployeeRestaurant(Long employeeId, Long restaurantId);
}