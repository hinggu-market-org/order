package com.yeol.market.order.application;

import com.yeol.market.order.application.dto.OrderResponse;
import com.yeol.market.order.application.dto.ProductResponseDto;
import com.yeol.market.order.application.dto.Top3NameResponse;
import com.yeol.market.order.domain.Order;
import com.yeol.market.order.domain.OrderHistory;
import com.yeol.market.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final OrderRepository orderRepository;
    private final StatisticClient statisticClient;
    private final ProductClient productClient;
    private final PointClient pointClient;

    public OrderResponse order(final Long memberId, final String menuUuId) {
        final ProductResponseDto product = productClient.getProductByUUID(menuUuId);
        final Order order = new Order(memberId, product.getUuid(), product.getPrice());
        pointClient.spend(order.getPaymentPrice());
        statisticClient.sendOrderHistory(new OrderHistory(memberId, product.getUuid(), product.getPrice()));
        orderRepository.save(order);
        return OrderResponse.of(order);
    }

    public Top3NameResponse getTop3Name() {
        final List<String> top3Name = orderRepository.findTop3ByCount();
        return Top3NameResponse.of(top3Name);
    }
}
