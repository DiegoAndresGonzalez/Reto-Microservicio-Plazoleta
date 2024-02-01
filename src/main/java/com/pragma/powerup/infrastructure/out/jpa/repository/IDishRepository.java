package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDishRepository extends JpaRepository<DishEntity,Long> {

    @Query("select d FROM DishEntity d WHERE d.categoryId.name = :category")
    Page<DishEntity> getAllDishes(@Param("category") String category, Pageable pageable);
    Optional<DishEntity> findDishByName (String name);
}