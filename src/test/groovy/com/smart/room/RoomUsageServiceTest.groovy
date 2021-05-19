package com.smart.room

import spock.lang.Specification

class RoomUsageServiceTest extends Specification {

    def guestRepositoryMock
    def roomUsageCalculableMock
    def subject

    def setup() {
        guestRepositoryMock = Mock(GuestRepository.class)
        roomUsageCalculableMock = Mock(RoomUsageCalculable.class)
        subject = new RoomUsageService(guestRepositoryMock, roomUsageCalculableMock)
    }

    def "should calculate rooms usage and value"() {
        given:
        def guests = GuestTestData.defaultGuests
        def request = RoomsUsageTestData.getDefaultRequest()
        def expected = RoomsUsageTestData.defaultResponse
        guestRepositoryMock.findAllGuests() >> guests
        roomUsageCalculableMock.calculate(request, guests) >> expected

        when:
        def actual = subject.calculate(request)

        then:
        actual == expected
    }
}
