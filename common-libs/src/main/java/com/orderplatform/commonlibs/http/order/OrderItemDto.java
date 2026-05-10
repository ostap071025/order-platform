package com.orderplatform.commonlibs.http.order;


import java.math.BigDecimal;

public record OrderItemDto(
        Long id,
        Integer quantity,
        BigDecimal priceAtPurchase,
        Long itemId
) {

}
