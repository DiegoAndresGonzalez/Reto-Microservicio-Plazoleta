package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.DishOrderModel;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishOrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {

    @Mapping(target = "status", source = "status")
    OrderEntity toOrderEntity(OrderModel orderModel);

    @Mapping(target = "status", source = "status")
    OrderModel toOrderModel(OrderEntity orderEntity);

    DishOrderEntity toDishOrderEntity(DishOrderModel dishOrderModel);

    List<DishOrderModel> toDishOrderModels(List<DishOrderEntity> dishOrderEntities);

    DishOrderModel dishOrderEntityToDishOrderModel(DishOrderEntity dishOrderEntity);

}