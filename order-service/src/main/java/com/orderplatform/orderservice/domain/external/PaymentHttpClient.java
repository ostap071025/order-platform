package com.orderplatform.orderservice.domain.external;

import com.orderplatform.commonlibs.http.payment.CreatePaymentRequestDto;
import com.orderplatform.commonlibs.http.payment.CreatePaymentResponseDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Locale;

@HttpExchange(
        accept = "application/json",
        contentType = "application/json",
        url = "/api/payments"
)
public interface PaymentHttpClient {

    @PostExchange
    CreatePaymentResponseDto createPayment(@RequestBody CreatePaymentRequestDto request);
}
