package com.smart.room;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
class RoomsRequest {

    @NotNull(message = "freePremiumRooms cannot be empty.")
    @Min(value = 0, message = "freePremiumRooms cannot be less than 0.")
    private final Integer freePremiumRooms;
    @NotNull(message = "freeEconomyRooms cannot be empty.")
    @Min(value = 0, message = "freeEconomyRooms cannot be less than 0.")
    private final Integer freeEconomyRooms;
}
