package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.client.UserClient;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import com.pragma.powerup.infrastructure.security.jwt.TokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final UserClient userClient;

    @Override
    public RestaurantModel createRestaurant(RestaurantModel restaurantModel) {
       String token = TokenHolder.getToken();
       UserModel responseModel = userClient.fetchUserModel(token, restaurantModel.getIdOwner());
       userClient.validateOwner(responseModel);
       RestaurantEntity restaurantEntity = restaurantRepository.save(restaurantEntityMapper.
               toRestaurantEntity(restaurantModel));
       return restaurantEntityMapper.toRestaurantModel(restaurantEntity);
    }

    @Override
    public RestaurantModel findRestaurantById(Long restaurantId) {
        return restaurantEntityMapper.toRestaurantModel
                (restaurantRepository.findById(restaurantId).orElse(null));
    }

    @Override
    public RestaurantModel findOwnerById(Long ownerId) {
        return restaurantEntityMapper.toRestaurantModel(
                restaurantRepository.findOwnerById(ownerId).orElse(null));
    }

    @Override
    public Page<RestaurantModel> getAllRestaurantsPaginated(Integer page, Integer size) {
        Page<RestaurantEntity> restaurantEntityPage = restaurantRepository.getAllRestaurants(PageRequest.of(page,size,Sort.by("name")));
        return restaurantEntityPage.map(restaurantEntityMapper::toRestaurantModel);
    }

}