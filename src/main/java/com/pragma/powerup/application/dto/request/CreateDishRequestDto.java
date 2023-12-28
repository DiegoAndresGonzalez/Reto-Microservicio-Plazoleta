package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDishRequestDto {

    private String name;
    private CategoryModel categoryId;
    private String description;
    private Integer price;
    private RestaurantModel restaurantId;
    private String imageUrl;

}