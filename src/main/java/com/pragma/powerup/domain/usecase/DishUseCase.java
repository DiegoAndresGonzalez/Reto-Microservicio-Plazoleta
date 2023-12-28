package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public void createDish(DishModel dishModel) {
        dishModel.setActive(true);
        dishPersistencePort.createDish(dishModel);
    }

    @Override
    public void updateDish(Long id, DishModel dishModel) {
        if (dishModel.getDescription() != null){
            dishModel.setDescription(dishModel.getDescription());
        }
        if (dishModel.getPrice() != null){
            dishModel.setPrice(dishModel.getPrice());
        }
        if (dishModel.getDescription() == null || dishModel.getPrice() == null) {
            throw new DomainException("Debes ingresar por lo menos un dato nuevo.");
        }
        dishPersistencePort.updateDish(id,dishModel);
    }

}