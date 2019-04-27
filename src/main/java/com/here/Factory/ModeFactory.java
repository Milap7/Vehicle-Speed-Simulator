package com.here.Factory;// import SafeMode.java;
// import SportMode.java;


import com.here.Mode.Mode;
import com.here.Mode.NormalMode;
import com.here.Mode.SafeMode;
import com.here.Mode.SportMode;

import java.util.Arrays;

public class ModeFactory extends Factory { //Factory that creates Normal Mode, Safe Mode or Sport Mode
    private enum Modes {
        NORMAL, SPORT, SAFE
    }
    private String mode;
    public ModeFactory(String mode) throws IllegalArgumentException {
        try {
            Arrays.asList(Modes.values()).contains(Modes.valueOf(mode)); // Check if entered Mode is valid
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Driver Mode can only be NORMAL or SAFE or SPORT"); //Else throw Exception
        }
        this.mode = mode;
    }

    /*Create Mode Object depending on Driving Mode */
    @Override
    public Mode getMode() {
        switch (mode) {
            case "NORMAL":
                return new NormalMode();
            case "SPORT":
                return new SportMode();
            default:
                return new SafeMode();
        }
    }
}