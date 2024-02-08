package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.DishOrderModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishOrderEntityMapper {

    DishOrderModel toDishOrderModel(DishOrderEntity dishOrderEntity);

}