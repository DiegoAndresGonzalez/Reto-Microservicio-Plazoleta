package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.DishModel;
import org.springframework.data.domain.Page;

public interface IDishPersistencePort {

    DishModel createDish(DishModel dishModel);
    DishModel findDishById(Long id);
    DishModel updateDish(DishModel dishModel);
    Page<DishModel> getAllDishesPaginated(String category, Integer page, Integer size);
}
