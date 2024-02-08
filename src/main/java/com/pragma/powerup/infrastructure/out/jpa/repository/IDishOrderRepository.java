package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.DishOrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDishOrderRepository extends JpaRepository<DishOrderEntity,Long> {

    List<DishOrderEntity> findByOrderId(OrderEntity orderEntity);

}