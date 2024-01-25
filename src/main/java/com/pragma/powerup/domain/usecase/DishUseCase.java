package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.exception.DataNotFoundException;
import com.pragma.powerup.domain.exception.DuplicateDataException;
import com.pragma.powerup.domain.exception.InvalidInputException;
import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import org.springframework.data.domain.Page;

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
    public void updateDish(DishModel dishModel) {
        DishModel foundDish = dishPersistencePort.findDishById(dishModel.getId());
        validateDishExistence(dishModel);
        validateUpdateInput(dishModel);
        validateUpdateDescription(foundDish, dishModel.getDescription());
        validateUpdatePrice(foundDish, dishModel);
        dishPersistencePort.updateDish(foundDish);
     }


    private void validateDishExistence(DishModel foundDish) {
        if (foundDish == null) {
            throw new DataNotFoundException("El plato no existe.");
        }
    }

    private void validateUpdateInput(DishModel dishModel) {
        if ((dishModel.getDescription() == null || dishModel.getDescription().trim().isEmpty())
                && (dishModel.getPrice() == null || dishModel.getPrice().trim().isEmpty())) {
            throw new InvalidInputException("Debes ingresar por lo menos un dato nuevo.");
        }
    }

    private void validateUpdateDescription(DishModel foundDish, String newDescription) {
        if (newDescription != null && !newDescription.trim().isEmpty()) {
            if (foundDish.getDescription().equals(newDescription)){
                throw new DuplicateDataException("La descripción que estás intentando ingresar " +
                        "es la misma que existe actualmente.");
            }
            foundDish.setDescription(newDescription);
        }
    }

    private void validateUpdatePrice(DishModel foundDish, DishModel dishModel) {
        if (dishModel.getPrice() != null && !dishModel.getPrice().trim().isEmpty()) {
            if (!dishModel.isPrice()) {
                throw new InvalidInputException("Ingresa el precio correctamente.");
            } else if (foundDish.getPrice().equals(dishModel.getPrice())){
                throw new DuplicateDataException("El precio que estás intentando ingresar " +
                        "es el mismo que existe actualmente.");
            }
            foundDish.setPrice(dishModel.getPrice());
        }
    }

    @Override
    public void updateDishStatus(DishModel dishModel) {
        DishModel foundDish = dishPersistencePort.findDishById(dishModel.getId());
        validateDishExistence(foundDish);
        validateStatusInput(dishModel);
        validateUpdateStatus(foundDish,dishModel.getActive());
        dishPersistencePort.updateDish(foundDish);
    }

    @Override
    public Page<DishModel> getAllDishesPaginated(String category, Integer page, Integer size) {
        return dishPersistencePort.getAllDishesPaginated(category,page,size);
    }

    private void validateStatusInput(DishModel dishModel) {
        if (dishModel.getActive() == null) {
            throw new InvalidInputException("Debes ingresar el nuevo estado del plato.");
        }
    }

    private void validateUpdateStatus(DishModel foundDish, Boolean newActive) {
        if (foundDish.getActive().equals(newActive)) {
                String message = Boolean.TRUE.equals(newActive) ?
                        "El plato ya se encuentra activado." : "El plato ya se encuentra desactivado.";
                throw new DuplicateDataException(message);
        }
        foundDish.setActive(newActive);
    }
}