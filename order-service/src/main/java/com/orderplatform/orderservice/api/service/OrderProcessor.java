package com.orderplatform.orderservice.api.service;

import com.orderplatform.orderservice.domen.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class OrderProcessor {

    private final OrderJpaRepository repository;

    public OrderEntity create(OrderEntity orderItemEntity) {
        return repository.save(orderItemEntity);
    }



    public OrderEntity getOrderOrThrow(Long id) {
        var orderItemEntityOptional = repository.findById(id);
        return orderItemEntityOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Entity with id '%s' not found", id)));
    }
}
