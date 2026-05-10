package com.orderplatform.commonlibs.http.order;

import com.orderplatform.commonlibs.http.order.OrderStatus;

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
        Set<OrderItemDto> items
) {

}
