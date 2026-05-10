package com.orderplatform.orderservice.api;

import com.orderplatform.commonlibs.http.payment.PaymentMethod;

public record OrderPaymentRequest(
        Long orderId,
        PaymentMethod paymentMethod
) {
}
