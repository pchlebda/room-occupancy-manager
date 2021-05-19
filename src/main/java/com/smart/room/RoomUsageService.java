package com.smart.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class RoomUsageService {

    private final GuestRepository guestRepository;
    private final RoomUsageCalculable roomUsageCalculable;

    RoomsResponse calculate(RoomsRequest roomsRequest) {
        List<Guest> allGuests = guestRepository.findAllGuests();
        return roomUsageCalculable.calculate(roomsRequest, allGuests);
    }
}
