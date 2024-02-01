package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.domain.utils.OrderStatus;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findOrderByClientIdAndStatus(Long id,OrderStatus status);

}