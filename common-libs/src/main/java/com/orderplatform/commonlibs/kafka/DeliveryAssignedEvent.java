package com.orderplatform.commonlibs.kafka;

import lombok.Builder;

@Builder
public record DeliveryAssignedEvent(
        Long orderId,
        Long courierId,
        String courierName,
        Integer etaMinutes
) {
}
