package com.yeol.market.order.domain;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String productName;

    private Long paymentPrice;

    private LocalDateTime createdTime;

    public Order(final String productName, final Long paymentPrice) {
        this.productName = productName;
        this.paymentPrice = paymentPrice;
        this.createdTime = LocalDateTime.now();
    }

    public Order(final Long memberId, final String productName, final Long paymentPrice) {
        this.memberId = memberId;
        this.productName = productName;
        this.paymentPrice = paymentPrice;
        this.createdTime = LocalDateTime.now();
    }
}
