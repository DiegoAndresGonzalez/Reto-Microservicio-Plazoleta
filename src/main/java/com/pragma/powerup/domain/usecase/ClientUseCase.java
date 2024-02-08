package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IClientServicePort;
import com.pragma.powerup.domain.exception.DataNotFoundException;
import com.pragma.powerup.domain.exception.InvalidInputException;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IClientPersistencePort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.utils.OrderStatus;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ClientUseCase implements IClientServicePort {

    private final IClientPersistencePort clientPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public ClientUseCase(IClientPersistencePort clientPersistencePort, IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void requestOrder(OrderModel orderModel) {
        validateData(orderModel);
        validateDishNames(orderModel.getDishNames());
        validateRestaurantAndDish(orderModel);
        orderModel.setStatus(OrderStatus.PENDIENTE);
        orderModel.setDate(Date.valueOf(LocalDate.now()));
        clientPersistencePort.requestOrder(orderModel);
    }

    private void validateRestaurantAndDish(OrderModel orderModel) {
        List<String> dishNames = orderModel.getDishNames();
        for (String dishName : dishNames) {
            DishModel dishModel = dishPersistencePort.findDishByName(dishName);

            RestaurantModel restaurantModel = restaurantPersistencePort.findRestaurantByName(orderModel.getRestaurant().getName());
            if (restaurantModel == null) {
                throw new DataNotFoundException("El restaurante ingresado no existe.");
            } else if (!restaurantModel.getId().equals(dishModel.getRestaurantId().getId())) {
                throw new InvalidInputException("El plato " + dishName + " no pertenece al restaurante elegido.");
            }
            orderModel.setRestaurant(restaurantModel);
        }
    }

    private void validateDishNames(List<String> dishNames) {
        for (String dishName : dishNames) {
            DishModel dishModel = dishPersistencePort.findDishByName(dishName);
            if (dishModel == null) {
                throw new DataNotFoundException("El plato ingresado no existe.");
            }
        }
    }

    private void validateData(OrderModel orderModel){
        if (orderModel == null || orderModel.getRestaurant() == null || orderModel.getDishNames() == null || orderModel.getDishNames().isEmpty()) {
            throw new InvalidInputException("Debes ingresar todos los datos para realizar un pedido.");
        }
    }

}