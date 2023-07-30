package com.yeol.market.order.infrastructure;

import com.yeol.market.order.application.PointClient;
import com.yeol.market.order.application.dto.PointResponseDto;
import com.yeol.market.order.application.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PointClientImpl implements PointClient {

    private final String productUrl;

    public PointClientImpl(@Value("${external.api.placeHolder}") final String productUrl) {
        this.productUrl = productUrl;
    }

    @Override
    public PointResponseDto spend(Long price) {
        final WebClient webClient = initWebclient();
        return webClient.post()
                .uri("/spend")
                .retrieve()
                .bodyToMono(PointResponseDto.class)
                .block();
    }

    private WebClient initWebclient() {
        return WebClient.builder()
                .baseUrl(productUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
