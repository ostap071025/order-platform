package com.orderplatform.orderservice.api;

import com.orderplatform.commonlibs.http.order.CreateOrderRequestDto;
import com.orderplatform.commonlibs.http.order.OrderDto;
import com.orderplatform.orderservice.domain.db.OrderEntityMapper;
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


    @PostMapping("/{id}/pay")
    public OrderDto payOrder(
            @RequestBody OrderPaymentRequest request,
            @PathVariable("id") Long id
    ){

        log.info("Paying order with id={}, request={}", id, request);
        var entity = orderProcessor.processPayment(id, request);
        return orderEntityMapper.toOrderDto(entity);
    }

    @GetMapping("/{id}")
    public OrderDto getOne(@PathVariable Long id) {

        log.info("Getting order with id={}", id);
        var found = orderProcessor.getOrderOrThrow(id);
        return orderEntityMapper.toOrderDto(found);
    }







}
