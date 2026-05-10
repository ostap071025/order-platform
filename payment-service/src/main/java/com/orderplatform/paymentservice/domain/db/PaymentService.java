package com.orderplatform.paymentservice.domain.db;

import com.orderplatform.commonlibs.http.payment.CreatePaymentRequestDto;
import com.orderplatform.commonlibs.http.payment.CreatePaymentResponseDto;
import com.orderplatform.commonlibs.http.payment.PaymentMethod;
import com.orderplatform.commonlibs.http.payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class PaymentService {

    private final PaymentEntityRepository repository;
    private final PaymentEntityMapper mapper;



    public CreatePaymentResponseDto create(CreatePaymentRequestDto request){

        log.info("Received request: paymentRequest={}", request);

        var found = repository.findByOrderId(request.orderId());
        if (found.isPresent()) {
            return mapper.toResponseDto(found.get());
        }
        return makePayment(request);

    }

    private CreatePaymentResponseDto makePayment(CreatePaymentRequestDto request) {
        var entity = mapper.toEntity(request);
        entity.setPaymentStatus(resolveStatus(request));
        var savedEntity = repository.save(entity);

        return mapper.toResponseDto(savedEntity);
    }



    private PaymentStatus resolveStatus(CreatePaymentRequestDto request) {
        return request.paymentMethod().equals(PaymentMethod.QR)
                ? PaymentStatus.PAYMENT_FAILED
                : PaymentStatus.PAYMENT_SUCCEEDED;
    }
}
