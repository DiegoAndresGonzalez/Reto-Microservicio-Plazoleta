package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.DishOrderModel;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.spi.IEmployeePersistencePort;
import com.pragma.powerup.domain.utils.OrderStatus;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishOrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishOrderRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EmployeeJpaAdapter implements IEmployeePersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IDishOrderRepository dishOrderRepository;

    @Override
    public Page<OrderModel> getOrderListPaginated(Integer page, Integer size, OrderStatus status) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderEntity> orderEntities = orderRepository.getAllOrdersByStatus(status,pageable);
        Map<Long,List<DishOrderEntity>> dishOrderEntities  = dishOrderRepository.findAll().stream()
                .collect(Collectors.groupingBy(dishOrderEntity ->
                                dishOrderEntity.getOrderId().getId()));
        return orderEntities.map(orderEntity ->{
                OrderModel orderModel = orderEntityMapper.toOrderModel(orderEntity);
                List<DishOrderModel> dishOrderModels = orderEntityMapper.toDishOrderModels(
                        dishOrderEntities.getOrDefault(orderModel.getId(),new ArrayList<>()));
                orderModel.setDishOrder(dishOrderModels);
                return orderModel;
        });
    }
}