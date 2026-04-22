package com.orderplatform.orderservice.domen;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import scala.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id", nullable = false)
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal priceAtPurchase;

    @Column(name = "item_id")
    private Long itemId;

    //таблиця order_items буде посилатися на orders_yt через order_id (join)
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

}
