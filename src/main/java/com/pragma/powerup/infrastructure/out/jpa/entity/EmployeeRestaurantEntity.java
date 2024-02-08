package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "empleado_restaurante")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeRestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_empleado")
    private Long employeeId;

    @Column(name = "id_restaurante")
    private Long restaurantId;

}