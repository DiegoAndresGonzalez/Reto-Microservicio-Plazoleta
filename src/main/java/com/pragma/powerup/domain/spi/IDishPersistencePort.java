package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.DishModel;

public interface IDishPersistencePort {

    DishModel createDish(DishModel dishModel);
    DishModel updateDish(Long id, DishModel dishModel);

}
