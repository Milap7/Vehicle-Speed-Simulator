package com.here.Event;

public class NormalEvent extends Event {
    public NormalEvent() {
        super();
    }

    /* Overriding the Getter Methods to return acceleration/deceleration according to the requirement of Normal Mode*/
    @Override
    public int getTrafficEvent() {
        return super.isTraffic ? 0 : -10; // Traffic Event cannot be applied if the Traffic State is already set.
    } 

    @Override
    public int getTrafficClearEvent() {
        return !super.isTraffic ? 0 : 10; // Traffic Clear Event cannot be applied if the Traffic State is not set(No Traffic Event was applied before)
    }

    @Override
    public int getWeatherRainyEvent() {
        return super.isWeatherRainy ? 0 : -5; // Weather Rainy Event cannot be applied if the Weather Rainy State is already set.
    }

    @Override
    public int getWeatherClearEvent() {
        return !super.isWeatherRainy ? 0 : 5; // Weather Clear Event cannot be applied if the Weather Rainy State is not set(No Weather Rainy Event was applied before)
    }

    @Override
    public int getSlipperyRoadEvent() {
        return super.isSlipperyRoad ? 0 : -15; // Slippery Road Event cannot be applied if the Slippery Road State is already set.
    }

    @Override
    public int getSlipperyRoadClearEvent() { // Slippery Road Clear Event cannot be applied if the Slippery Road State is not set(No Slippery Road Event was applied before)
        return !super.isSlipperyRoad ? 0 : 15;
    }

    @Override
    public int getTurboEvent() {
        return super.isTurbo || super.isSlipperyRoad ? 0 : 20; // Turbo event cannot be applied if the turbo state is already set(turbo applied before) OR is Slippery Road State is already set too.
    } // A new speed limit sign resets the Turbo State

    @Override
    public int getSpeedLimitX(int inputEvent){ // If the Speed Limit Event is valid( <100; already checked by the Validator class), then return the Event.
        return inputEvent;
    }
}