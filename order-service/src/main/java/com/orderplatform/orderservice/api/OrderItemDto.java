package com.orderplatform.orderservice.api;

import scala.math.BigDecimal;

public record OrderItemDto(
        Long id,
        Integer quantity,
        BigDecimal priceAtPurchase,
        Long itemId
) {

}
