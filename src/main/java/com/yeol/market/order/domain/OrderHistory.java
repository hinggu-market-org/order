package com.yeol.market.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {

    private Long memberId;
    private String productName;
    private Long paymentPrice;

}
