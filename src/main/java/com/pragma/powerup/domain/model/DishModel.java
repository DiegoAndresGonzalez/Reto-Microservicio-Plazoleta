package com.pragma.powerup.domain.model;

import static com.pragma.powerup.domain.utils.Constant.DISH_PRICE_REGEX_PATTERN;

public class DishModel {

    private Long id;
    private String name;
    private CategoryModel categoryId;
    private String description;
    private String price;
    private RestaurantModel restaurantId;
    private String imageUrl;
    private Boolean active;

    public DishModel() {
    }

    public DishModel(Long id, String name, CategoryModel categoryId, String description, String price, RestaurantModel restaurantId, String imageUrl, Boolean active) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
        this.imageUrl = imageUrl;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryModel getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryModel categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public RestaurantModel getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantModel restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public boolean isPrice(){
        return this.price.matches(DISH_PRICE_REGEX_PATTERN);
    }

}