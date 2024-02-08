package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.exception.InvalidInputException;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import org.springframework.data.domain.Page;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void createRestaurant(RestaurantModel restaurantModel) {
        validateName(restaurantModel);
        validateAddress(restaurantModel);
        validateNit(restaurantModel);
        validatePhone(restaurantModel);
        validateUrlLogo(restaurantModel);
        validateIdOwner(restaurantModel);
        restaurantPersistencePort.createRestaurant(restaurantModel);
    }

    @Override
    public RestaurantModel findRestaurantByName(String name) {
        return restaurantPersistencePort.findRestaurantByName(name);
    }

    @Override
    public Page<RestaurantModel> getAllRestaurantsPaginated(Integer page, Integer size) {
        return restaurantPersistencePort.getAllRestaurantsPaginated(page,size);
    }

    @Override
    public void saveEmployeeRestaurant(Long employeeId, Long restaurantId) {
        restaurantPersistencePort.saveEmployeeRestaurant(employeeId,restaurantId);
    }

    private void validateName(RestaurantModel restaurantModel){
        if (!restaurantModel.isName()){
            throw new InvalidInputException("El nombre del restaurante no puede tener únicamente números.");
        }
        if (restaurantModel.getName() == null || restaurantModel.getName().trim().isEmpty()) {
            throw new InvalidInputException("El correo no puede estar vacío.");
        }

        RestaurantModel foundRestaurant = restaurantPersistencePort.findRestaurantByName(restaurantModel.getName());
        if (foundRestaurant != null){
            throw new InvalidInputException("El nombre del restaurante ya existe.");
        }
    }

    private void validateNit(RestaurantModel restaurantModel){
        if (restaurantModel.getNit() == null || restaurantModel.getNit().trim().isEmpty()) {
            throw new InvalidInputException("El NIT no puede estar vacío.");
        } else if (!restaurantModel.isNit()) {
            throw new InvalidInputException("El NIT debe contener solo caracteres numéricos");
        }
    }

    private void validateAddress(RestaurantModel restaurantModel){
        if (restaurantModel.getAddress() == null || restaurantModel.getAddress().trim().isEmpty()) {
            throw new InvalidInputException("La dirección no puede estar vacía.");
        }
    }

    private void validatePhone(RestaurantModel restaurantModel){
        if (restaurantModel.getPhone() == null || restaurantModel.getPhone().trim().isEmpty()) {
            throw new InvalidInputException("El teléfono no puede estar vacío.");
        } else if (!restaurantModel.isPhone()) {
        throw new InvalidInputException("Ingresa el telefono del restaurante correctamente.");
        }
    }

    private void validateUrlLogo(RestaurantModel restaurantModel){
        if (restaurantModel.getUrlLogo() == null || restaurantModel.getUrlLogo().trim().isEmpty()) {
            throw new InvalidInputException("La URL del logo no puede estar vacía.");
        }
    }

    private void validateIdOwner(RestaurantModel restaurantModel) {
        if (restaurantModel.getIdOwner() == null || restaurantModel.toString().trim().isEmpty()) {
            throw new InvalidInputException("El ID del propietario no puede estar vacío.");
        }
    }

}