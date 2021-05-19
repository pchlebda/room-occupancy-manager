package com.smart.room

class GuestTestData {

    static repository = new MockedGuestsRepository()

    static def getDefaultGuests() {
        return repository.findAllGuests()
    }
}
