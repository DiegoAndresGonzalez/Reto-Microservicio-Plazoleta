package com.pragma.powerup.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.pragma.powerup.domain.utils.Constant.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantModel {

    private Long id;
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String urlLogo;
    private Long idOwner;

    public boolean isNit(){
        return this.nit.matches(NIT_REGEX_PATTERN);
    }

    public boolean isPhone(){
        return this.phone.matches(PHONE_REGEX_PATTERN);
    }

    public boolean isName(){
        return this.name.matches(RESTAURANT_NAME_REGEX_PATTERN);
    }
}