package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.DishModel;

public interface IDishPersistencePort {

    DishModel createDish(DishModel dishModel);
    DishModel findDishById(Long id);
    DishModel updateDish(DishModel dishModel);

}
