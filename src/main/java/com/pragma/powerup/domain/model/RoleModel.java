package com.pragma.powerup.domain.model;

public class RoleModel {
    private Long id;
    private String role;

    public RoleModel(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public RoleModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}