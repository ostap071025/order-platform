package com.orderplatform.commonlibs.http.payment;

import com.orderplatform.commonlibs.http.payment.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreatePaymentRequestDto(
    Long orderId,
    PaymentMethod paymentMethod,
    BigDecimal amount
) {

}
