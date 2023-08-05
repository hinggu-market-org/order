package com.yeol.market.order.ui;

import com.yeol.market.order.application.OrderService;
import com.yeol.market.order.application.dto.OrderResponse;
import com.yeol.market.order.application.dto.Top3NameResponse;
import com.yeol.market.order.ui.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> orderProduct(@RequestBody final OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.order(orderRequest.getMemberId(), orderRequest.getMenuUuId()));
    }

    @GetMapping("/top3")
    public ResponseEntity<Top3NameResponse> getTop3ProductId() {
        return ResponseEntity.ok(orderService.getTop3Name());
    }
}
