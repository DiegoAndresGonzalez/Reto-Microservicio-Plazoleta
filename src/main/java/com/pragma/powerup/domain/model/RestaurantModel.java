package com.pragma.powerup.domain.model;

public class RestaurantModel {

    private Long id;
    private String name;
    private Integer nit;
    private String address;
    private String phone;
    private String urlLogo;
    private Long idProprietary;

    public RestaurantModel(Long id, String name, Integer nit, String address, String phone, String urlLogo, Long idProprietary) {
        this.name = name;
        this.nit = nit;
        this.address = address;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.idProprietary = idProprietary;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
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

    public Long getIdProprietary() {
        return idProprietary;
    }

    public void setIdProprietary(Long idProprietary) {
        this.idProprietary = idProprietary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}