package com.pragma.powerup.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.pragma.powerup.domain.utils.Constant.DISH_PRICE_REGEX_PATTERN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishModel {

    private Long id;
    private String name;
    private CategoryModel categoryId;
    private String description;
    private String price;
    private RestaurantModel restaurantId;
    private String imageUrl;
    private Boolean active;

    public boolean isPrice(){
        return this.price.matches(DISH_PRICE_REGEX_PATTERN);
    }

}