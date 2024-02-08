package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.EmployeeResponseDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.mapper.IOrderRequestMapper;
import com.pragma.powerup.application.mapper.IOrderResponseMapper;
import com.pragma.powerup.domain.api.IClientServicePort;
import com.pragma.powerup.domain.api.IEmployeeServicePort;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.utils.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {

    private final IOrderRequestMapper orderRequestMapper;
    private final IClientServicePort clientServicePort;
    private final IEmployeeServicePort employeeServicePort;
    private final IOrderResponseMapper orderResponseMapper;

    @Override
    public void requestOrder(OrderRequestDto orderRequestDto) {
        OrderModel orderModel = orderRequestMapper.toOrderModel(orderRequestDto);
        clientServicePort.requestOrder(orderModel);
    }

    @Override
    public EmployeeResponseDto getOrderListPaginated(Integer page, Integer size, OrderStatus status) {
        Page<OrderModel> orderPage = employeeServicePort.getOrderListPaginated(page,size,status);
        List<OrderResponseDto> orderResponseDto = orderPage.getContent().stream()
                .map(orderResponseMapper::toOrderResponseDto)
                .collect(Collectors.toList());
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setOrderModels(orderResponseDto);
        return employeeResponseDto;
    }
}