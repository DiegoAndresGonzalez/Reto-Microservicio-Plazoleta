package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.DishModel;

public interface IDishServicePort {

    void createDish(DishModel dishModel);
    void updateDish(DishModel dishModel);
    void updateDishStatus(DishModel dishModel);
}