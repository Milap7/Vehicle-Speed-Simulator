package com.here.Mode;

public class SportMode extends Mode {
    public SportMode() {
        super();
        super.mode = "SPORT";
    }

    /* Overriding the Getter Methods to return acceleration/deceleration according to the requirement of Sport Mode*/
    @Override
    public void changeSpeed(int newSpeed, int inputEvent) {
        try {
            if (inputEvent < 10) {
                if (CURRENT_SPEED + newSpeed < 10) {
                    CURRENT_SPEED = 10;
                } else {
                    CURRENT_SPEED = CURRENT_SPEED + newSpeed;
                }
            } else {
                CURRENT_SPEED = newSpeed + 5; // Return X + 5 for Speed Limit X Event
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}