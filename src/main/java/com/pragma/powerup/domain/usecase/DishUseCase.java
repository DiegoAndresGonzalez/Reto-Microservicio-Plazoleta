package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.exception.DataNotFoundException;
import com.pragma.powerup.domain.exception.InvalidInputException;
import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, ICategoryPersistencePort categoryPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void createDish(DishModel dishModel) {
        validateName(dishModel);
        validateCategory(dishModel);
        validateDescription(dishModel);
        validatePrice(dishModel);
        validateRestaurant(dishModel);
        validateImageUrl(dishModel);
        dishModel.setActive(true);
        dishPersistencePort.createDish(dishModel);
    }

    private void validateName(DishModel dishModel) {
        if (dishModel.getName() == null || dishModel.getName().trim().isEmpty()) {
            throw new InvalidInputException("El nombre del plato no puede estar vacío.");
        }
    }

    private void validateCategory(DishModel dishModel) {
        if (dishModel.getCategoryId() == null) {
            throw new InvalidInputException("La categoría del plato no puede estar vacía.");
        }

        CategoryModel categoryModel = categoryPersistencePort.findCategoryById(dishModel.getCategoryId().getId());
        if (categoryModel == null){
            throw new DataNotFoundException("La categoría no existe.");
        }

    }

    private void validateDescription(DishModel dishModel) {
        if (dishModel.getDescription() == null || dishModel.getDescription().trim().isEmpty()) {
            throw new InvalidInputException("La descripción del plato no puede estar vacía.");
        }
    }

    private void validatePrice(DishModel dishModel) {
        if (dishModel.getPrice() == null || dishModel.getPrice().trim().isEmpty()) {
            throw new InvalidInputException("El precio del plato no puede estar vacío.");
        } else if (!dishModel.isPrice()){
            throw new InvalidInputException("Ingresa el precio correctamente.");
        }
    }

    private void validateRestaurant(DishModel dishModel) {
        if (dishModel.getRestaurantId() == null) {
            throw new InvalidInputException("El id del restaurante no puede estar vacío.");
        }
        RestaurantModel restaurantModel = restaurantPersistencePort.findRestaurantById(dishModel.getRestaurantId().getId());
        if (restaurantModel == null) {
            throw new DataNotFoundException("El restaurante no existe.");
        }
    }

    private void validateImageUrl(DishModel dishModel) {
        if (dishModel.getImageUrl() == null || dishModel.getImageUrl().trim().isEmpty()) {
            throw new InvalidInputException("La URL de la imagen del plato no puede estar vacía.");
        }
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
            throw new InvalidInputException("Debes ingresar por lo menos un dato nuevo.");
        }
        dishPersistencePort.updateDish(id,dishModel);
     }
}




