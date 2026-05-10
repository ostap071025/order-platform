package com.orderplatform.orderservice.domain.db;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal priceAtPurchase;

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_id")
    private Long itemId;

    //таблиця order_items буде посилатися на orders_yt через order_id (join)
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

}
