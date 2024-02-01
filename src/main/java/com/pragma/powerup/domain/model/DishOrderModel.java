package com.pragma.powerup.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishOrderModel {

    private Long id;
    private OrderModel orderId;
    private List<DishModel> dishOrder;
    private Integer amount;

    public List<String> getDishName(){
        return dishOrder.stream().map
                (DishModel::getName).collect(Collectors.toList());
    }

}