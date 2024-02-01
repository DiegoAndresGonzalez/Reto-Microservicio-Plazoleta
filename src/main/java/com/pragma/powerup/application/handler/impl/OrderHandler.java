package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.mapper.IOrderRequestMapper;
import com.pragma.powerup.domain.api.IClientServicePort;
import com.pragma.powerup.domain.model.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {

    private final IOrderRequestMapper orderRequestMapper;
    private final IClientServicePort clientServicePort;

    @Override
    public void requestOrder(OrderRequestDto orderRequestDto) {
        OrderModel orderModel = orderRequestMapper.toOrderModel(orderRequestDto);
        clientServicePort.requestOrder(orderModel);
    }
}