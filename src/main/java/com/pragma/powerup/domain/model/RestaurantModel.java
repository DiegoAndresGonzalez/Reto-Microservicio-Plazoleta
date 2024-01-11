package com.pragma.powerup.domain.model;

import static com.pragma.powerup.domain.utils.Constant.*;

public class RestaurantModel {

    private Long id;
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String urlLogo;
    private Long idOwner;

    public RestaurantModel(Long id, String name, String nit, String address, String phone, String urlLogo, Long idOwner) {
        this.name = name;
        this.nit = nit;
        this.address = address;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.idOwner = idOwner;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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