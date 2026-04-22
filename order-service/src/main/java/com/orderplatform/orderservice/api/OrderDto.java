package com.orderplatform.orderservice.api;

import com.orderplatform.orderservice.domen.OrderStatus;
import java.math.BigDecimal;
import java.util.Set;

public record OrderDto(
        long id,
        long customerId,
        String address,
        BigDecimal totalAmount,
        String courierName,
        Integer etaMinutes,
        OrderStatus orderStatus,
        Set<OrderItemDto> orderItemEntities
) {

}
