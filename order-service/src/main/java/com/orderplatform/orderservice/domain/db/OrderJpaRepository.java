package com.orderplatform.orderservice.domain.db;

import org.springframework.data.jpa.repository.JpaRepository;
//ти автоматично отримуєш:
    //save() findById() findAll() deleteById()
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

}
