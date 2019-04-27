package com.here.Factory;

import com.here.Event.Event;
import com.here.Event.NormalEvent;
import com.here.Event.SafeEvent;
import com.here.Event.SportEvent;

public class EventFactory extends Factory { // Factory that creates NormalEvent, SafeEvent or SportEvent
    private String event;
    public EventFactory(String event) {
        this.event = event;
    }

    /*Create Event Object depending on Driving Mode */
    @Override
    public Event getEvent() {
        switch (event) {
            case "NORMAL":
                return new NormalEvent();
            case "SAFE":
                return new SafeEvent();
            default:
                return new SportEvent();
        }
    }
}