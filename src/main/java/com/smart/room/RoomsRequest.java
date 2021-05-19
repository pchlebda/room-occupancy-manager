package com.smart.room;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class RoomsRequest {
    private final int freePremiumRooms;
    private final int freeEconomyRooms;
}
