package com.yeol.market.order.application;

import com.yeol.market.order.application.dto.PointResponseDto;

public interface PointClient {

    PointResponseDto spend(Long price);
}
