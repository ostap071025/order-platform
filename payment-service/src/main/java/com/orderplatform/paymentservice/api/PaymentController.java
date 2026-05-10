package com.orderplatform.paymentservice.api;

import com.orderplatform.commonlibs.http.payment.CreatePaymentRequestDto;
import com.orderplatform.commonlibs.http.payment.CreatePaymentResponseDto;
import com.orderplatform.paymentservice.domain.db.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/create")
    public CreatePaymentResponseDto create(
            @RequestBody CreatePaymentRequestDto request
    ){
        log.info("Received request: paymentRequest={}", request);
        return paymentService.create(request);

    }



}
