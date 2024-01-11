package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.RestaurantModel;

public interface IRestaurantPersistencePort {

    RestaurantModel createRestaurant(RestaurantModel restaurantModel);
    RestaurantModel findRestaurantById(Long restaurantId);
}