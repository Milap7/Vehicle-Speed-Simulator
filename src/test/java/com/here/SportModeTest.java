package com.here;

import static org.junit.Assert.assertEquals;


import com.here.Event.SportEvent;
import com.here.Mode.SportMode;
import org.junit.Before;
import org.junit.Test;


public class SportModeTest {

    private SportMode sportMode;
    private SportEvent sportEvent;
    private int NEW_SPEED;

    @Before
    public void setup() {
        sportMode = new SportMode();
        sportEvent = new SportEvent();
    }

    @Test
    public void testPrintMode() {
        assertEquals("SPORT", sportMode.printMode());
    }

    /* When Sport Mode is Initialized, initial speed is set to 20
    Thus when traffic event is applied, results in deceleration of -5kph,
    thus the final speed after the event is 15 kph*/
    @Test
    public void testChangeSpeedforSportModeforTrafficEvent() {
        NEW_SPEED = sportEvent.getNewSpeed(1);
        sportMode.changeSpeed(NEW_SPEED, 1);
        assertEquals(15, sportMode.CURRENT_SPEED);
    }

    /* First TrafficEvent is applied, which causes deceleration of -5,
    the resulting speed is 15 kph.
    Traffic Clear can only be applied if there was a Traffic event applied before,
    thus results in acceleration of 5, which results in final speed of 20kph. */
    @Test
    public void testChangeSpeedforSportModeforTrafficEventClear() {
        sportMode.changeSpeed(sportEvent.getNewSpeed(1),1);
        NEW_SPEED = sportEvent.getNewSpeed(2);
        sportMode.changeSpeed(NEW_SPEED,2);
        assertEquals(20,sportMode.CURRENT_SPEED);
    }

    /* TrafficClear Event cannot be applied without a prior Traffic Event, if it is given as input
    There is not acceleration, and the speed remains same(20).
    */
    @Test
    public void NoChangeOfSpeedforSportModeforTrafficClearEventWithoutTrafficEvent() {
        NEW_SPEED = sportEvent.getNewSpeed(2);
        sportMode.changeSpeed(NEW_SPEED,2);
        assertEquals(20, sportMode.CURRENT_SPEED);
    }

    /* When the Weather Rainy Event is applied, it causes a deceleration of 5 kph for Sport Mode.
    resulting in final speed of 15 kph
     */
    @Test
    public void testChangeOfSpeedforSportModeforWeartherEvent() {
        NEW_SPEED = sportEvent.getNewSpeed(3);
        sportMode.changeSpeed(NEW_SPEED, 3);
        assertEquals(15, sportMode.CURRENT_SPEED);
    }

    /* First Weather Rainy is applied, which causes deceleration of 5,
    Weather Clear can only be applied if there was a Weather Rainy event applied before,
    thus results in acceleration of 5, which results in final speed of 20kph. */
    @Test
    public void testChangeOfSpeedforSportModeforWeatherClearEvent() {
        sportMode.changeSpeed(sportEvent.getNewSpeed(3),3);
        NEW_SPEED = sportEvent.getNewSpeed(4);
        sportMode.changeSpeed(NEW_SPEED,4);
        assertEquals(20, sportMode.CURRENT_SPEED);
    }

    /* Weather Clear Event cannot be applied without a prior Weather Rainy Event, if it is given as input
    There is not acceleration, and the speed remains same(20 kph).
     */
    @Test
    public void NoChangeOfSpeedforSportModeforWeatherEventClearWithoutWeatherRainyEvent() {
        NEW_SPEED = sportEvent.getNewSpeed(4);
        sportMode.changeSpeed(NEW_SPEED,4);
        assertEquals(20,sportMode.CURRENT_SPEED);
    }

    /* When the SlipperyRoad Event is applied, it causes a deceleration of 15 kph for Sport Mode.
   For initial speed of 20 kph, the event should result in 5 kph, but since current speed cannot drop below
   10 kph, the final speed is 10 kph
    */
    @Test
    public void testChangeOfSpeedforSportModeforSlipperyRoadEvent() {
        NEW_SPEED = sportEvent.getNewSpeed(5);
        sportMode.changeSpeed(NEW_SPEED, 5);
        assertEquals(10, sportMode.CURRENT_SPEED);
    }

    /* First Slippery Road Event is applied, which causes deceleration of 15,
    since speed cannot drop below 10, the resulting speed is 10 kph.
    Slippery Road Clear can only be applied if there was a Slippery Road event applied before,
    thus results in acceleration of 15, which results in final speed of 25kph. */
    @Test
    public void testChangeOfSpeedforSportModeforSlipperyRoadClearEvent() {
        sportMode.changeSpeed(sportEvent.getNewSpeed(5),5);
        NEW_SPEED = sportEvent.getNewSpeed(6);
        sportMode.changeSpeed(NEW_SPEED,6);
        assertEquals(25, sportMode.CURRENT_SPEED);
    }

    /* Slippery Road Clear Event cannot be applied without a prior Slippery Road Event, if it is given as input
    There is not acceleration, and the speed remains same(20 kph).
    */
    @Test
    public void NoChangeOfSpeedforSportModeforSlipperyRoadClearEventWithoutSlipperyRoadEvent() {
        NEW_SPEED = sportEvent.getNewSpeed(6);
        sportMode.changeSpeed(NEW_SPEED,6);
        assertEquals(20,sportMode.CURRENT_SPEED);
    }

    /* When a Emergency Turbo event is applied, in Sport mode, an acceleration fo 30 kph is applied,
    thus resulting in speed of 50kph
     */
    @Test
    public void testChangeOfSpeedforSportModeforTurboEvent() {
        NEW_SPEED = sportEvent.getNewSpeed(7);
        sportMode.changeSpeed(NEW_SPEED,7);
        assertEquals(50,sportMode.CURRENT_SPEED);
    }

    /* Emergency Turbo cannot be applied if Slippery Road Event was applied before
     */
    @Test
    public void NoChangeOfSpeedforSportModeforTurboEventIfSlipperyRoadEventWasAppliedBefore() {
        sportMode.changeSpeed(sportEvent.getNewSpeed(5),5);
        NEW_SPEED = sportEvent.getNewSpeed(7);
        sportMode.changeSpeed(NEW_SPEED,7);
        assertEquals(10, sportMode.CURRENT_SPEED);
    }

    /* Emergency Turbo can be applied only once, thus when applied first time, resulted in acceleration of 30kph
    for Sport Mode, when applied the second time, there was not change in speed and resulting speed is 50 kph.
     */
    @Test
    public void NoChangeOfSpeedforSportModeforTurboEventIfTurboEventWasAppliedBefore() {
        sportMode.changeSpeed(sportEvent.getNewSpeed(7),7);
        NEW_SPEED = sportEvent.getNewSpeed(7);
        sportMode.changeSpeed(NEW_SPEED,7);
        assertEquals(50, sportMode.CURRENT_SPEED);
    }

    /* When Speed Limit X event is applied in Sport Mode, the current speed is set to that Speed Limit +5 kph.
    Does not work for speed limit for more than 99 kph.
     */
    @Test
    public void testChangeSpeedforSportModeforSpeedLimitEvent() {
        NEW_SPEED = sportEvent.getNewSpeed(98);
        sportMode.changeSpeed(NEW_SPEED, 98);
        assertEquals(103, sportMode.CURRENT_SPEED);
    }

    @Test
    public void testNoChangeOfSpeedForEvents8() {
        NEW_SPEED = sportEvent.getNewSpeed(8);
        sportMode.changeSpeed(NEW_SPEED,8);
        assertEquals(20, sportMode.CURRENT_SPEED);
    }

    @Test
    public void testNoChangeOfSpeedForEvents9() {
        NEW_SPEED = sportEvent.getNewSpeed(9);
        sportMode.changeSpeed(NEW_SPEED,9);
        assertEquals(20, sportMode.CURRENT_SPEED);
    }

    @Test
    public void speedCannotDropBelow10Sport() {
        NEW_SPEED = sportEvent.getNewSpeed(10);
        sportMode.changeSpeed(NEW_SPEED,10);
        NEW_SPEED = sportEvent.getNewSpeed(5);
        sportMode.changeSpeed(NEW_SPEED,5);
        assertEquals(10, sportMode.CURRENT_SPEED);
    }
}
