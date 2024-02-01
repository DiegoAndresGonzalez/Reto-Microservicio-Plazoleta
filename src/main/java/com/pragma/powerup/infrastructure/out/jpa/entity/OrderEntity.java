package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.utils.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente")
    private Long clientId;

    @Column(name = "fecha")
    private Date date;

    @OneToMany(mappedBy = "orderId")
    private List<DishOrderEntity> dishOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private OrderStatus status;

    @Column(name = "id_chef")
    private Long chefId;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private RestaurantEntity restaurant;

}