package com.orderplatform.orderservice.api;

import com.orderplatform.commonlibs.http.order.CreateOrderRequestDto;
import com.orderplatform.commonlibs.http.order.OrderStatus;
import com.orderplatform.commonlibs.http.payment.CreatePaymentRequestDto;
import com.orderplatform.commonlibs.http.payment.CreatePaymentResponseDto;
import com.orderplatform.commonlibs.http.payment.PaymentStatus;
import com.orderplatform.commonlibs.kafka.OrderPaidEvent;
import com.orderplatform.orderservice.domain.db.OrderEntity;
import com.orderplatform.orderservice.domain.db.OrderEntityMapper;
import com.orderplatform.orderservice.domain.db.OrderItemEntity;
import com.orderplatform.orderservice.domain.db.OrderJpaRepository;
import com.orderplatform.orderservice.domain.external.PaymentHttpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderProcessor {

    private final OrderJpaRepository repository;
    private final OrderEntityMapper orderEntityMapper;
    private final PaymentHttpClient paymentHttpClient;
    private final KafkaTemplate<Long, OrderPaidEvent> kafkaTemplate;

    @Value("${order.paid.topic}")
    private String orderPaidTopic;

    public OrderEntity create(CreateOrderRequestDto request) {
        var entity = orderEntityMapper.toEntity(request);
        calculatePricingForOrder(entity);
        entity.setOrderStatus(OrderStatus.PENDING_PAYMENT);
        return repository.save(entity);
    }


    public OrderEntity getOrderOrThrow(Long id) {
        var orderItemEntityOptional = repository.findById(id);
        return orderItemEntityOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Entity with id '%s' not found", id)));
    }

    private void calculatePricingForOrder(OrderEntity entity) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItemEntity item: entity.getItems()) {
            var randomPrice = ThreadLocalRandom.current().nextDouble(100, 5000);
            item.setPriceAtPurchase(BigDecimal.valueOf(randomPrice));

            totalPrice = item.getPriceAtPurchase()
                    .multiply(BigDecimal.valueOf(item.getQuantity()))
                    .add(totalPrice);
        }
        entity.setTotalAmount(totalPrice);

    }

    public OrderEntity processPayment(
            Long id,
            OrderPaymentRequest request
    ) {
        var entity = getOrderOrThrow(id);
        if (!entity.getOrderStatus().equals(OrderStatus.PENDING_PAYMENT)) {
            throw new RuntimeException("Order must be in status Pending_Payment");
        }
        var response = paymentHttpClient.createPayment(CreatePaymentRequestDto.builder()
                        .orderId(id)
                        .paymentMethod(request.paymentMethod())
                        .amount(entity.getTotalAmount())
                .build());

        var status = response.paymentStatus().equals(PaymentStatus.PAYMENT_SUCCEEDED)
                ? OrderStatus.PAID
                : OrderStatus.PAYMENT_FAILED;
        entity.setOrderStatus(status);
        sendOrderPaidEvent(entity, response);
        return repository.save(entity);

    }


    private void sendOrderPaidEvent(
            OrderEntity entity,
            CreatePaymentResponseDto responseDto
    ) {
        kafkaTemplate.send(
                orderPaidTopic,
                entity.getId(),
                OrderPaidEvent.builder()
                        .orderId(entity.getId())
                        .amount(entity.getTotalAmount())
                        .paymentMethod(responseDto.paymentMethod())
                        .paymentId(responseDto.paymentId())
                        .build()
        ).thenAccept(result -> {
            log.info("Order PAID event sent with id={}", entity.getId());
        });
    }


}
