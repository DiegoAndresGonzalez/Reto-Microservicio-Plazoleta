package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.feignclient.IUserFeignClient;
import com.pragma.powerup.infrastructure.out.jpa.client.UserClient;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IUserFeignClient userFeignClient;

    @Override
    public RestaurantModel createRestaurant(RestaurantModel restaurantModel) {
       UserModel responseModel = userFeignClient.fetchUserModel()
       RestaurantEntity restaurantEntity = restaurantRepository.save(restaurantEntityMapper.
               toRestaurantEntity(restaurantModel));
       return restaurantEntityMapper.toRestaurantModel(restaurantEntity);
    }

    @Override
    public RestaurantModel findRestaurantById(Long restaurantId) {
        return restaurantEntityMapper.toRestaurantModel
                (restaurantRepository.findById(restaurantId).orElse(null));
    }

}