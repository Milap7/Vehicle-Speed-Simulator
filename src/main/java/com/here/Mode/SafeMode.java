package com.here.Mode;

public class SafeMode extends Mode {
    public SafeMode() {
        super();
        super.mode = "SAFE";
    }

    /* Overriding the Getter Methods to return acceleration/deceleration according to the requirement of Safe Mode*/
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
                if(newSpeed >= 15) {
                    CURRENT_SPEED = newSpeed - 5; // Return X-5 for Speed Limit X Event
                }
                else {
                    CURRENT_SPEED = 10;
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}