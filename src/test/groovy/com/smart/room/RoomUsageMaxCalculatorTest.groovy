package com.smart.room

import spock.lang.Specification
import spock.lang.Unroll

class RoomUsageMaxCalculatorTest extends Specification {

    def subject = new RoomUsageMaxCalculator()

    @Unroll
    def "calculate room usage and value"(int freePremiumRooms, int freeEconomyRooms, int expectedPremiumUsage, int expectedEconomyUsage, BigDecimal expectedPremiumValue, BigDecimal expectedEconomyValue) {
        given:
        def request = new RoomsRequest(freePremiumRooms, freeEconomyRooms)
        def defaultGuests = GuestTestData.defaultGuests

        expect:
        def actual = subject.calculate(request, defaultGuests)
        actual.premiumRoomUsage == expectedPremiumUsage
        actual.economyRoomUsage == expectedEconomyUsage
        actual.economyRoomTotalValue == expectedEconomyValue
        actual.premiumRoomTotalValue == expectedPremiumValue

        where:
        freePremiumRooms | freeEconomyRooms | expectedPremiumUsage | expectedEconomyUsage | expectedPremiumValue        | expectedEconomyValue
        3                | 3                | 3                    | 3                    | BigDecimal.valueOf(738)     | BigDecimal.valueOf(167.99)
        7                | 5                | 6                    | 4                    | BigDecimal.valueOf(1054)    | BigDecimal.valueOf(189.99)
        2                | 7                | 2                    | 4                    | BigDecimal.valueOf(583)     | BigDecimal.valueOf(189.99)
        7                | 1                | 7                    | 1                    | BigDecimal.valueOf(1153.99) | BigDecimal.valueOf(45)
        0                | 0                | 0                    | 0                    | BigDecimal.valueOf(0)       | BigDecimal.valueOf(0)
        1                | 1                | 1                    | 1                    | BigDecimal.valueOf(374)     | BigDecimal.valueOf(99.99)
    }
}
