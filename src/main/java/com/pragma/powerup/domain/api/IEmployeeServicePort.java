package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.utils.OrderStatus;
import org.springframework.data.domain.Page;

public interface IEmployeeServicePort {
    Page<OrderModel> getOrderListPaginated(Integer page, Integer size, OrderStatus status);
}