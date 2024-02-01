package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.DishModel;
import org.springframework.data.domain.Page;

public interface IDishServicePort {

    void createDish(DishModel dishModel);
    void updateDish(DishModel dishModel);
    void updateDishStatus(DishModel dishModel);
    Page<DishModel> getAllDishesPaginated(String category, Integer page, Integer size);
}