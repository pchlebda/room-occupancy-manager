package com.smart.room;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Repository
class MockedGuestsRepository implements GuestRepository {
    @Override
    public List<Guest> findAllGuests() {
        return DoubleStream.of(23, 45, 155, 374, 22, 99.99d, 100, 101, 115, 209)
                .mapToObj(BigDecimal::valueOf)
                .map(Guest::new)
                .collect(Collectors.toList());
    }
}
