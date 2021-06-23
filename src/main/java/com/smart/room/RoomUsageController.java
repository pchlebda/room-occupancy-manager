package com.smart.room;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class RoomUsageController {

    private final RoomUsageService roomUsageService;

    @PostMapping("/api/v1/room")
    RoomsResponse calculate(@Valid @RequestBody RoomsRequest roomsRequest) {
        return roomUsageService.calculate(roomsRequest);
    }
}
