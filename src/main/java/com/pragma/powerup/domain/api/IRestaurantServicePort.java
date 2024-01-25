package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.RestaurantModel;
import org.springframework.data.domain.Page;

public interface IRestaurantServicePort {

    void createRestaurant(RestaurantModel restaurantModel);
    Page<RestaurantModel> getAllRestaurantsPaginated(Integer page, Integer size);
}