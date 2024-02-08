package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.exception.InvalidInputException;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IClientPersistencePort;
import com.pragma.powerup.domain.utils.OrderStatus;
import com.pragma.powerup.infrastructure.feignclient.IUserFeignClient;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishOrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishOrderRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import com.pragma.powerup.infrastructure.security.jwt.TokenHolder;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ClientJpaAdapter implements IClientPersistencePort {

    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderRepository orderRepository;
    private final IDishOrderRepository dishOrderRepository;
    private final IDishRepository dishRepository;
    private final IUserFeignClient userFeignClient;

    @Override
    public OrderModel requestOrder(OrderModel orderModel) {
        UserModel userModel = userFeignClient.fetchUser(TokenHolder.getBearer());
        Optional<OrderEntity> foundOrder = orderRepository.findOrderByClientIdAndStatus
                (userModel.getId(),OrderStatus.PENDIENTE);
        if (foundOrder.isPresent()){
            throw new InvalidInputException("Ya tienes un pedido pendiente.");
        }
        orderModel.setClientId(userModel.getId());
        OrderEntity orderEntity = orderRepository.save(
                orderEntityMapper.toOrderEntity(orderModel));
        List<DishOrderEntity> dishOrderEntities = orderModel.getDishOrder()
                .stream().map(dishOrderModel -> {
                    DishOrderEntity dishOrderEntity = orderEntityMapper.toDishOrderEntity(dishOrderModel);
                    dishOrderEntity.setOrderId(orderEntity);
                    Optional<DishEntity> dishEntity = dishRepository.findDishByName(dishOrderModel.getDishModels().getName());
                    dishEntity.ifPresent(dishOrderEntity::setDishModels);
                    return dishOrderEntity;
                })
                .collect(Collectors.toList());
        dishOrderRepository.saveAll(dishOrderEntities);
        return orderEntityMapper.toOrderModel(orderEntity);
    }
}