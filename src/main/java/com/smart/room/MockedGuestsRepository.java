package com.smart.room;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Repository
class MockedGuestsRepository implements GuestRepository {

    private volatile List<Guest> guest = null;

    @Override
    public List<Guest> findAllGuests() {
        if (guest == null) {
            synchronized (this) {
                if (guest == null) {
                    this.guest = DoubleStream.of(23, 45, 155, 374, 22, 99.99d, 100, 101, 115, 209)
                            .mapToObj(BigDecimal::valueOf)
                            .map(Guest::new)
                            .collect(Collectors.toList());
                }
            }
        }
        return guest;
    }
}
