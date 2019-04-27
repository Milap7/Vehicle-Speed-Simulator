package com.here.Mode;

public class NormalMode extends Mode{
    
    public NormalMode() {
        super();
        super.mode = "NORMAL";
    }

    /* Overriding the Getter Methods to return acceleration/deceleration according to the requirement of NormalMode*/
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
                CURRENT_SPEED = newSpeed; // Return X for Speed Limit X Event
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}