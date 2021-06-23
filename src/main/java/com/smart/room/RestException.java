package com.smart.room;

class RestException {

    private final String message;

    RestException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
