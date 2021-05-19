package com.smart.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
class RoomsResponse {

    private final int premiumRoomUsage;
    private final int economyRoomUsage;
    private final BigDecimal premiumRoomTotalValue;
    private final BigDecimal economyRoomTotalValue;
    private final String currency = "EUR";
}
