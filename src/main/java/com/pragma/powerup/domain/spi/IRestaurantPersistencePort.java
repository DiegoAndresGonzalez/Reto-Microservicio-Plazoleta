package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.EmployeeRestaurantModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import org.springframework.data.domain.Page;

public interface IRestaurantPersistencePort {
    RestaurantModel createRestaurant(RestaurantModel restaurantModel);
    RestaurantModel findRestaurantById(Long restaurantId);
    RestaurantModel findOwnerById(Long ownerId);
    RestaurantModel findRestaurantByName(String restaurantName);
    Page<RestaurantModel> getAllRestaurantsPaginated(Integer page, Integer size);
    EmployeeRestaurantModel saveEmployeeRestaurant(Long employeeId,Long restaurantId);
}