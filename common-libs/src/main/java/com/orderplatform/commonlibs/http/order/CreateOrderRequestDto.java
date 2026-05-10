package com.orderplatform.commonlibs.http.order;

import com.orderplatform.commonlibs.http.order.OrderItemDto;

import java.util.Set;

public record CreateOrderRequestDto(
        Long customerId,
        String address,
        Set<OrderItemRequestDto> items

) {


}
