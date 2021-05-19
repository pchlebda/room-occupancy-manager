package com.smart.room

class RoomsUsageTestData {

    static def getDefaultRequest() {
        return new RoomsRequest(3, 3);
    }

    static def getDefaultResponse() {
        return RoomsResponse.builder()
                .premiumRoomTotalValue(BigDecimal.valueOf(738))
                .economyRoomTotalValue(BigDecimal.valueOf(167.99))
                .economyRoomUsage(3)
                .premiumRoomUsage(3)
                .build()
    }
}
