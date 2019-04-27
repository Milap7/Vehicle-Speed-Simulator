package com.here.Event;

public abstract class Event {
    
    boolean isTraffic = false; //Holds thee Traffic Event state
    boolean isWeatherRainy = false; //Holds the Weather Rainy event state
    boolean isSlipperyRoad = false; //Holds the SlipperRoad Event state
    boolean isTurbo = false; // Holds the Turbo Event state

    /* Returns acceleration or deceleration depending on the Input Event*/
    public int getNewSpeed(int inputEvent) {
        int accelerate,decelerate;
        switch (inputEvent) {
            case 1:
                decelerate = getTrafficEvent(); // Event 1 = Traffic Event
                setTrafficState(true);// Sets the Traffic Event state
                return decelerate; //Returns deceleration value depending on Driving Mode
            case 2:
                accelerate = getTrafficClearEvent(); // Event 2 = Traffic Clear Event
                setTrafficState(false);// Resets(Clears) the Traffic Event State
                return accelerate; //Returns acceleration value depending on Driving Mode
            case 3:
                decelerate = getWeatherRainyEvent(); // Event 3 = WeatherRainy Event
                setWeatherState(true); //Sets the Weather Rainy Event State
                return decelerate;
            case 4:
                accelerate = getWeatherClearEvent(); // Event 4 = WeatherClear Event
                setWeatherState(false);// Resets the Weather Clear Event State
                return accelerate;
            case 5:
                decelerate = getSlipperyRoadEvent(); // Event 5 = Slippery Road Event
                setSlipperyRoadState(true); // Sets the Slippery Road State
                return decelerate;
            case 6:
                accelerate = getSlipperyRoadClearEvent(); // Event 6 = Slippery Road Clear Event
                setSlipperyRoadState(false); // Resets the Slippery Road Sate
                return accelerate;
            case 7: // Event 7 = Emergency Turbo Event
                if (isSlipperyRoad) { // Emergency Turbo cannot be applied if Slippery Road State is Set
                    return 0;
                } else {
                    accelerate = getTurboEvent();
                    setTurboState(true); //Sets the Turbo State, Turbo cannot be applied if Turbo State is set
                    return accelerate;
                }

            case 8:
            case 9:
                return 0;
            default:
                setTurboState(false); // A Speed Limit X Event clears(resets) the Turbo State
                return getSpeedLimitX(inputEvent); //Returns acceleration, if Speed Limit Event is Valid(<100)
        }
    }

    /* Setter Methods to change States*/
    private void setTrafficState(boolean tf) {
        this.isTraffic = tf;
    }

    private void setWeatherState(boolean tf) {
        this.isWeatherRainy = tf;
    }
    
    private void setSlipperyRoadState(boolean tf) {
        this.isSlipperyRoad = tf;
    }

    private void setTurboState(boolean tf) {
        this.isTurbo = tf;
    }

    /* Getter Methods to get acceleration/deceleration for each Event*/
    public int getTrafficEvent() {
        return 0;
    }

    public int getTrafficClearEvent() {
        return 0;
    }

    public int getWeatherRainyEvent() {
        return 0;
    }

    public int getWeatherClearEvent() {
        return 0;
    }

    public int getSlipperyRoadEvent() {
        return 0;
    }

    public int getSlipperyRoadClearEvent() {
        return 0;
    }

    public int getTurboEvent() {
        return 0;
    }

    public int getSpeedLimitX(int inputEvent) {
        return inputEvent;
    }
   
}