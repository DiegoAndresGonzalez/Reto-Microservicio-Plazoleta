package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.client.UserClient;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.powerup.infrastructure.security.jwt.TokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final UserClient userClient;

    @Override
    public DishModel createDish(DishModel dishModel) {
        RestaurantModel restaurantModel = restaurantPersistencePort.findRestaurantById(dishModel.getRestaurantId().getId());
        userClient.validateOwnership(TokenHolder.getBearer(),restaurantModel);
        DishEntity dishEntity = dishRepository.save(dishEntityMapper.toDishEntity(dishModel));
        return dishEntityMapper.toDishModel(dishEntity);
    }

    @Override
    public DishModel findDishById(Long id) {
        return dishEntityMapper.toDishModel
                (dishRepository.findById(id).orElse(null));
    }

    @Override
    public DishModel findDishByName(String name) {
        Optional<DishEntity> dishEntity = dishRepository.findDishByName(name);
        return dishEntity.map(dishEntityMapper::toDishModel).orElse(null);
    }

    @Override
    public DishModel updateDish(DishModel dishModel) {
        RestaurantModel restaurantModel = restaurantPersistencePort.findRestaurantById(dishModel.getRestaurantId().getId());
        userClient.validateOwnership(TokenHolder.getBearer(),restaurantModel);
        return dishEntityMapper.toDishModel(dishRepository
                .save(dishEntityMapper.toDishEntity(dishModel)));
    }

    @Override
    public Page<DishModel> getAllDishesPaginated(String category, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<DishEntity> dishEntityPage = dishRepository.getAllDishes(category,pageable);
        return dishEntityPage.map(dishEntityMapper::toDishModel);
    }

}