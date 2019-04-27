package com.here.Factory;

import com.here.Event.Event;
import com.here.Mode.Mode;

public abstract class Factory { // Factory class for the Factory Design Pattern; creates required Mode and Event Objects
    Mode mode = null;
    Event event = null;
    public Mode getMode() { //Return required Mode
        return mode;
    }
    public Event getEvent() {
        return event;
    } // Return required Event

}