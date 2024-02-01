package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.DishOrderModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {

    private RestaurantModel restaurant;
    private List<DishOrderModel> dishOrder;

}