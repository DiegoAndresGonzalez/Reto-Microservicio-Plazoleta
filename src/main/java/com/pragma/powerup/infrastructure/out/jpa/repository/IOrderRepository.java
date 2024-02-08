package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.domain.utils.OrderStatus;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findOrderByClientIdAndStatus(Long id,OrderStatus status);

    @Query("select o FROM OrderEntity o WHERE o.status = :status")
    Page<OrderEntity> getAllOrdersByStatus(@Param("status") OrderStatus status, Pageable pageable);

}