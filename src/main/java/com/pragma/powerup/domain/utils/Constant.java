package com.pragma.powerup.domain.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String OWNER_ROLE = "PROPIETARIO";
    public static final String ADMIN_ROLE = "ADMINISTRADOR";
    public static final String NIT_REGEX_PATTERN = "\\d+";
    public static final String PHONE_REGEX_PATTERN = "^\\+?[0-9]{10,13}$";
    public static final String RESTAURANT_NAME_REGEX_PATTERN = "(?=.*[a-zA-Z]).*";
    public static final String DISH_PRICE_REGEX_PATTERN = "^[1-9]\\d*$";
    public static final String SECRET_KEY = "bLbjRGox3VMtwGXzsFh51hL4AADgiqjMTxbzId3RSWJd9aU3KFC8flGRrrNIprvz";


}