package com.here.Mode;

public abstract class Mode {
   
    String mode = null;
    public int CURRENT_SPEED = 20; // Define current speed, all acceleration and decelerations are applied to current speed in all modes

    public void printSpeed() {
        System.out.println(CURRENT_SPEED);
    }
    /* Method to apply the acceleration or deceleration calculated by the event*/
    public void changeSpeed(int newSpeed, int inputEvent) {
    }

    public String printMode() {
        return this.mode;
    }

}