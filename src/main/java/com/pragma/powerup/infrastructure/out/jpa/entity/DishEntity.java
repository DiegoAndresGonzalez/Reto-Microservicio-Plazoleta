package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "platos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "nombre")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoryEntity categoryId;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "precio")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private RestaurantEntity restaurantId;

    @Column(name = "url_imagen")
    private String imageUrl;

    @Column(name = "activo", columnDefinition = "BOOLEAN")
    private Boolean active;
}