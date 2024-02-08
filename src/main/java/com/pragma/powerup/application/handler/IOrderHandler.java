package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.EmployeeResponseDto;
import com.pragma.powerup.domain.utils.OrderStatus;
import org.springframework.data.domain.Page;

public interface IOrderHandler {

    void requestOrder(OrderRequestDto orderRequestDto);
    EmployeeResponseDto getOrderListPaginated(Integer page, Integer size, OrderStatus status);

}