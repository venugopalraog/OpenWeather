package com.sample.openweather.models.events;

/**
 * Created by venugopalraog on 10/16/17.
 */

public class NetworkResponseFailedEvent {
    private String message;

    public NetworkResponseFailedEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
