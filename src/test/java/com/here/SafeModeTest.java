package com.here;
import static org.junit.Assert.assertEquals;

import com.here.Event.SafeEvent;
import com.here.Mode.SafeMode;
import org.junit.Before;
import org.junit.Test;


public class SafeModeTest {



    private SafeMode safeMode;
    private SafeEvent safeEvent;
    private int NEW_SPEED;

    @Before
    public void setup() {
        safeMode = new SafeMode();
        safeEvent = new SafeEvent();
    }

    @Test
    public void testPrintMode() {
        assertEquals("SAFE", safeMode.printMode());
    }

    /* When Safe Mode is Initialized, initial speed is set to 20
    Thus when traffic event is applied, results in deceleration of -15kph, but the speed of the vehicle cannot drop below
    10kph, thus the final speed after the event is 10 kph*/
    @Test
    public void testChangeSpeedforSafeModeforTrafficEvent() {
        NEW_SPEED = safeEvent.getNewSpeed(1);
        safeMode.changeSpeed(NEW_SPEED, 1);
        assertEquals(10, safeMode.CURRENT_SPEED);
    }

    /* First TrafficEvent is applied, which causes deceleration of -15, but as the vehicle speed cannot drop below
    10kp, the resulting speed is 10 kph.
    Traffic Clear can only be applied if there was a Traffic event applied before,
    thus results in acceleration of 15, which results in final speed of 25kph. */
    @Test
    public void testChangeSpeedforSafeModeforTrafficEventClear() {
        safeMode.changeSpeed(safeEvent.getNewSpeed(1),1);
        NEW_SPEED = safeEvent.getNewSpeed(2);
        safeMode.changeSpeed(NEW_SPEED,2);
        assertEquals(25,safeMode.CURRENT_SPEED);
    }

    /* TrafficClear Event cannot be applied without a prior Traffic Event, if it is given as input
    There is not acceleration, and the speed remains same(20).
    */
    @Test
    public void NoChangeOfSpeedforSafeModeforTrafficClearEventWithoutTrafficEvent() {
        NEW_SPEED = safeEvent.getNewSpeed(2);
        safeMode.changeSpeed(NEW_SPEED,2);
        assertEquals(20, safeMode.CURRENT_SPEED);
    }

    /* When the Weather Rainy Event is applied, it causes a deceleration of 5 kph for Safe Mode.
    resulting in final speed of 15 kph
     */
    @Test
    public void testChangeOfSpeedforSafeModeforWeartherEvent() {
        NEW_SPEED = safeEvent.getNewSpeed(3);
        safeMode.changeSpeed(NEW_SPEED, 3);
        assertEquals(15, safeMode.CURRENT_SPEED);
    }

    /* First Weather Rainy is applied, which causes deceleration of 5,
    Weather Clear can only be applied if there was a Weather Rainy event applied before,
    thus results in acceleration of 5, which results in final speed of 20kph. */
    @Test
    public void testChangeOfSpeedforSafeModeforWeatherClearEvent() {
        safeMode.changeSpeed(safeEvent.getNewSpeed(3),3);
        NEW_SPEED = safeEvent.getNewSpeed(4);
        safeMode.changeSpeed(NEW_SPEED,4);
        assertEquals(20, safeMode.CURRENT_SPEED);
    }

    /* Weather Clear Event cannot be applied without a prior Weather Rainy Event, if it is given as input
    There is not acceleration, and the speed remains same(20 kph).
     */
    @Test
    public void NoChangeOfSpeedforSafeModeforWeatherEventClearWithoutWeatherRainyEvent() {
        NEW_SPEED = safeEvent.getNewSpeed(4);
        safeMode.changeSpeed(NEW_SPEED,4);
        assertEquals(20,safeMode.CURRENT_SPEED);
    }

    /* When the SlipperyRoad Event is applied, it causes a deceleration of 15 kph for Safe Mode.
    For initial speed of 20 kph, the event should result in 5 kph, but since current speed cannot drop below
    10 kph, the final speed is 10 kph
     */
    @Test
    public void testChangeOfSpeedforSafeModeforSlipperyRoadEvent() {
        NEW_SPEED = safeEvent.getNewSpeed(5);
        safeMode.changeSpeed(NEW_SPEED, 5);
        assertEquals(10, safeMode.CURRENT_SPEED);
    }

    /* First Slippery Road Event is applied, which causes deceleration of 15,
    since speed cannot drop below 10, the resulting speed is 10 kph.
    Slippery Road Clear can only be applied if there was a Slippery Road event applied before,
    thus results in acceleration of 15, which results in final speed of 25kph. */
    @Test
    public void testChangeOfSpeedforSafeModeforSlipperyRoadClearEvent() {
        safeMode.changeSpeed(safeEvent.getNewSpeed(5),5);
        NEW_SPEED = safeEvent.getNewSpeed(6);
        safeMode.changeSpeed(NEW_SPEED,6);
        assertEquals(25, safeMode.CURRENT_SPEED);
    }

    /* Slippery Road Clear Event cannot be applied without a prior Slippery Road Event, if it is given as input
    There is not acceleration, and the speed remains same(20 kph).
    */
    @Test
    public void NoChangeOfSpeedforSafeModeforSlipperyRoadClearEventWithoutSlipperyRoadEvent() {
        NEW_SPEED = safeEvent.getNewSpeed(6);
        safeMode.changeSpeed(NEW_SPEED,6);
        assertEquals(20,safeMode.CURRENT_SPEED);
    }

    /* When a Emergency Turbo event is applied, in Safe mode, an acceleration fo 10 kph is applied,
    thus resulting in speed of 30kph
     */
    @Test
    public void testChangeOfSpeedforSafeModeforTurboEvent() {
        NEW_SPEED = safeEvent.getNewSpeed(7);
        safeMode.changeSpeed(NEW_SPEED,7);
        assertEquals(30,safeMode.CURRENT_SPEED);
    }

    /* Emergency Turbo cannot be applied if Slippery Road Event was applied before
     */
    @Test
    public void NoChangeOfSpeedforSafeModeforTurboEventIfSlipperyRoadEventWasAppliedBefore() {
        safeMode.changeSpeed(safeEvent.getNewSpeed(5),5);
        NEW_SPEED = safeEvent.getNewSpeed(7);
        safeMode.changeSpeed(NEW_SPEED,7);
        assertEquals(10, safeMode.CURRENT_SPEED);
    }

    /* Emergency Turbo can be applied only once, thus when applied first time, resulted in acceleration of 10kph
    for Safe Mode, when applied the second time, there was not change in speed and resulting speed is 30 kph.
     */
    @Test
    public void NoChangeOfSpeedforSafeModeforTurboEventIfTurboEventWasAppliedBefore() {
        safeMode.changeSpeed(safeEvent.getNewSpeed(7),7);
        NEW_SPEED = safeEvent.getNewSpeed(7);
        safeMode.changeSpeed(NEW_SPEED,7);
        assertEquals(30, safeMode.CURRENT_SPEED);
    }

    /* When Speed Limit X event is applied in Safe Mode, the current speed is set to that Speed Limit -5 kph.
    Does not work for speed limit for more than 99 kph.
     */
    @Test
    public void testChangeSpeedforSafeModeforSpeedLimitEvent() {
        NEW_SPEED = safeEvent.getNewSpeed(98);
        safeMode.changeSpeed(NEW_SPEED, 98);
        assertEquals(93, safeMode.CURRENT_SPEED);
    }

    @Test
    public void testNoChangeOfSpeedForEvents8() {
        NEW_SPEED = safeEvent.getNewSpeed(8);
        safeMode.changeSpeed(NEW_SPEED,8);
        assertEquals(20, safeMode.CURRENT_SPEED);
    }

    @Test
    public void testNoChangeOfSpeedForEvents9() {
        NEW_SPEED = safeEvent.getNewSpeed(9);
        safeMode.changeSpeed(NEW_SPEED,9);
        assertEquals(20, safeMode.CURRENT_SPEED);
    }

    /*When a Speed Limit of 10 event is applied, mathematically should return vehicle speed of 5 kph,
    Since Speed Limit of X results in X-5; but since the vehicle speed cannot drop below 10; the vehicle speed
    is maintained at 10kph
     */
    @Test
    public void testForSpeedDoesNotFallBelow10ForSpeedLimit10() {
        NEW_SPEED = safeEvent.getNewSpeed(10);
        safeMode.changeSpeed(NEW_SPEED,10);
        assertEquals(10, safeMode.CURRENT_SPEED);
    }
}
