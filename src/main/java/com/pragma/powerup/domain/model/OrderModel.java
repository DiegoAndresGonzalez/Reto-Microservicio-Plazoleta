package com.pragma.powerup.domain.model;

import com.pragma.powerup.domain.utils.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {

    private Long id;
    private Long clientId;
    private Date date;
    private List<DishOrderModel> dishOrder;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Long chefId;
    private RestaurantModel restaurant;

    public List<String> getDishNames(){
        return dishOrder.stream()
                .flatMap(dishOrderModel -> dishOrderModel.getDishName().stream())
                .collect(Collectors.toList());
    }
}