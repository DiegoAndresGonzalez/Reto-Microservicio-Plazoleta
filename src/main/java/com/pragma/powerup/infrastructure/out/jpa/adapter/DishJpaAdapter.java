package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public DishModel createDish(DishModel dishModel) {
        DishEntity dishEntity = dishRepository.save(dishEntityMapper.toDishEntity(dishModel));
        return dishEntityMapper.toDishModel(dishEntity);
    }

    @Override
    public DishModel findDishById(Long id) {
        return dishEntityMapper.toDishModel
                (dishRepository.findById(id).orElse(null));
    }

    @Override
    public DishModel updateDish(DishModel dishModel) {
        return dishEntityMapper.toDishModel(dishRepository
                .save(dishEntityMapper.toDishEntity(dishModel)));
    }

}