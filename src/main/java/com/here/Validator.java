package com.here;

public  class Validator {

    /* Method to check if the input event is valid, all events > 100 are considered Invalid*/
    public boolean isValid(int inputEvent) {
        if(inputEvent >= 1 && inputEvent <= 100) {
            return true;
        }
        else {
            return false;
        }
    }
}
