package com.smart.room

import spock.lang.Specification

import java.util.stream.Collectors

class MockedGuestsRepositoryTest extends Specification {

    def subject = new MockedGuestsRepository()

    def "should find all guests"() {
        when:
        def actual = subject.findAllGuests()

        then:
        actual.size() == 10
        actual.stream().map { it.offerPrice }.collect(Collectors.toList()) == [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
    }
}
