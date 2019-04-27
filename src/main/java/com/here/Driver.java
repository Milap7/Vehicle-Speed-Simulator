package com.here;

import com.here.Event.Event;
import com.here.Factory.EventFactory;
import com.here.Factory.Factory;
import com.here.Factory.ModeFactory;
import com.here.Mode.Mode;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Driver  {
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        Factory modeFactory = new ModeFactory(args[0]); //Instantiate the factory objects
        Factory eventFactory = new EventFactory(args[0]);

        Mode mode = modeFactory.getMode(); //Create the object of required Driving Mode
        Event event = eventFactory.getEvent(); //Create the object of required Mode-Event
        System.out.println("Welcome. Driving mode is " + mode.printMode());
        mode.printSpeed();

        Scanner scan = new Scanner(System.in);
        Validator validator = new Validator(); //Create object of Validator
        int inputEvent;

        while(true) {
            System.out.print("Enter Event: ");
            try {
                inputEvent = scan.nextInt();
                int newSpeed;
                if(validator.isValid(inputEvent)) { //Validate the Input Event
                    newSpeed = event.getNewSpeed(inputEvent); //Get the appropriate acceleration or deceleration
                    mode.changeSpeed(newSpeed,inputEvent); //Apply the acceleration or deceleration to Current Speed
                    mode.printSpeed();
                }
                else {
                    System.out.println("Invalid"); //Print Invalid for invalid(>100) events and continue process
                }
            }catch (InputMismatchException e) {
                throw new InputMismatchException("Input Events can only range from 1 to 100");
            }
        }
    }
}