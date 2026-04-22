package com.orderplatform.orderservice.api.controller;

import com.orderplatform.orderservice.api.OrderDto;
import com.orderplatform.orderservice.api.service.OrderProcessor;
import com.orderplatform.orderservice.domen.OrderEntity;
import com.orderplatform.orderservice.domen.OrderEntityMapper;
import com.orderplatform.orderservice.domen.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProcessor orderProcessor;
    private final OrderEntityMapper orderEntityMapper;
    private static Logger log = LoggerFactory.getLogger(OrderProcessor.class);


    @PostMapping("/create")
    public OrderDto create(@RequestBody OrderItemEntity orderItemEntity){

        log.info("Creating order with id={}", orderItemEntity.getId());
        var saved = orderProcessor.create(orderItemEntity);
        return orderEntityMapper.toOrderDto(saved);
    }

    @GetMapping("/{id}")
    public OrderDto getOne(@PathVariable("{id}") Long id) {

        log.info("Getting order with id={}", id);
        var found = orderProcessor.getOrderOrThrow(id);
        return orderEntityMapper.toOrderDto(found);
    }







}
