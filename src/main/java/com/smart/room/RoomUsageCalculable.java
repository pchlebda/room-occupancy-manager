package com.smart.room;

import java.util.List;

interface RoomUsageCalculable {

    RoomsResponse calculate(RoomsRequest roomsRequest, List<Guest> guests);
}
