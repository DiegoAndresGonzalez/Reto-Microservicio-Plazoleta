package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.OrderModel;

public interface IClientPersistencePort {

    OrderModel requestOrder(OrderModel orderModel);

}