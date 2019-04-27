package com.here.Event;

public class SafeEvent extends Event {
    public SafeEvent() {
        super();
    }

    /* Overriding the Getter Methods to return acceleration/deceleration according to the requirement of Safe Mode*/
    @Override
    public int getTrafficEvent() {
        return super.isTraffic ? 0 : -15;
    } 

    @Override
    public int getTrafficClearEvent() {
        return !super.isTraffic ? 0 : 15;
    }

    @Override
    public int getWeatherRainyEvent() {
        return super.isWeatherRainy ? 0 : -5;
    }

    @Override
    public int getWeatherClearEvent() {
        return !super.isWeatherRainy ? 0 : 5;
    }

    @Override
    public int getSlipperyRoadEvent() {
        return super.isSlipperyRoad ? 0 : -15;
    }

    @Override
    public int getSlipperyRoadClearEvent() {
        return !super.isSlipperyRoad ? 0 : 15;
    }

    @Override
    public int getTurboEvent() {
        return super.isTurbo ? 0 : 10;
    }
    @Override
    public int getSpeedLimitX(int inputEvent){
        return inputEvent;
    }

}