package com.pragma.powerup.domain.model;

public class UserModel {

    private Long id;
    private RoleModel role;

    public UserModel(Long id, RoleModel role) {
        this.id = id;
        this.role = role;
    }

    public UserModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

}