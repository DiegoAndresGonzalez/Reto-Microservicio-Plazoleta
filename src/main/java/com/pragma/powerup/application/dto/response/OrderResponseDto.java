package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.utils.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {

    private EmployeeRestaurantResponseDto restaurant;
    private Long id;
    private Long clientId;
    private Date date;
    private List<DishOrderResponseDto> dishOrder;
    private OrderStatus status;
    private Long chefId;

}