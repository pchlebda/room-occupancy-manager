package com.smart.room

import spock.lang.Specification

class RoomUsageControllerTest extends Specification {

    def subject
    def roomUsageServiceMock

    def setup() {
        roomUsageServiceMock = Mock(RoomUsageService.class)
        subject = new RoomUsageController(roomUsageServiceMock)
    }

    def "should calculate usage and value"() {
        given:
        def expected = RoomsUsageTestData.getDefaultResponse()
        def request = RoomsUsageTestData.getDefaultRequest()
        roomUsageServiceMock.calculate(request) >> expected

        when:
        def actual = subject.calculate(request)

        then:
        actual == expected
    }
}
