package com.orderplatform.commonlibs.http.payment;

import com.orderplatform.commonlibs.http.payment.PaymentMethod;
import com.orderplatform.commonlibs.http.payment.PaymentStatus;

import java.math.BigDecimal;

public record CreatePaymentResponseDto(
        Long paymentId,
        Long orderId,
        BigDecimal amount,
        PaymentStatus paymentStatus,
        PaymentMethod paymentMethod
) {

}
