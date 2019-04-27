package com.here;
import static org.junit.Assert.assertEquals;

import com.here.Event.NormalEvent;
import com.here.Mode.NormalMode;
import org.junit.Before;
import org.junit.Test;


public class NormalModeTest {

    private NormalMode normalMode;
    private NormalEvent normalEvent;
    private int NEW_SPEED;

    //The Initial Speed is set to 20 kph
    @Before
    public void setup() {
        normalMode = new NormalMode();
        normalEvent = new NormalEvent();
    }

    @Test
    public void testPrintMode() {
        assertEquals("NORMAL", normalMode.printMode());
    }

    /* When Normal Mode is Initialized, initial speed is set to 20
    Thus when traffic event is applied, results in -10kph. */
    @Test
    public void testChangeSpeedforNormalModeforTrafficEvent() {
        NEW_SPEED = normalEvent.getNewSpeed(1);
        normalMode.changeSpeed(NEW_SPEED, 1);
        assertEquals(10, normalMode.CURRENT_SPEED);
    }

    /* First TrafficEvent is applied, which causes deceleration of 10
    Traffic Clear can only be applied if there was a Traffic event applied before,
    thus results in acceleration of 10, which results in final speed of 20kph. */
    @Test
    public void testChangeSpeedforNormalModeforTrafficEventClear() {
        normalMode.changeSpeed(normalEvent.getNewSpeed(1),1);
        NEW_SPEED = normalEvent.getNewSpeed(2);
        normalMode.changeSpeed(NEW_SPEED,2);
        assertEquals(20,normalMode.CURRENT_SPEED);
    }

    /* TrafficClear Event cannot be applied without a prior Traffic Event, if it is given as input
    There is not acceleration, and the speed remains same(20).
     */
    @Test
    public void NoChangeOfSpeedforNormalModeforTrafficClearEventWithoutTrafficEvent() {
        NEW_SPEED = normalEvent.getNewSpeed(2);
        normalMode.changeSpeed(NEW_SPEED,2);
        assertEquals(20,normalMode.CURRENT_SPEED);
    }

    /* When the Weather Rainy Event is applied, it causes a deceleration of 5 kph for Normal Mode.
    resulting in final speed of 15 kph
     */
    @Test
    public void testChangeOfSpeedforNormalModeforWeartherEvent() {
        NEW_SPEED = normalEvent.getNewSpeed(3);
        normalMode.changeSpeed(NEW_SPEED, 3);
        assertEquals(15, normalMode.CURRENT_SPEED);
    }

    /* First Weather Rainy is applied, which causes deceleration of 5,
    Weather Clear can only be applied if there was a Weather Rainy event applied before,
    thus results in acceleration of 5, which results in final speed of 20kph. */
    @Test
    public void testChangeOfSpeedforNormalModeforWeatherClearEvent() {
        normalMode.changeSpeed(normalEvent.getNewSpeed(3),3);
        NEW_SPEED = normalEvent.getNewSpeed(4);
        normalMode.changeSpeed(NEW_SPEED,4);
        assertEquals(20,normalMode.CURRENT_SPEED);
    }

    /* Weather Clear Event cannot be applied without a prior Weather Rainy Event, if it is given as input
    There is not acceleration, and the speed remains same(20 kph).
     */
    @Test
    public void NoChangeOfSpeedforNormalModeforWeatherEventClearWithoutWeatherRainyEvent() {
        NEW_SPEED = normalEvent.getNewSpeed(4);
        normalMode.changeSpeed(NEW_SPEED,4);
        assertEquals(20,normalMode.CURRENT_SPEED);
    }

    /* When the SlipperyRoad Event is applied, it causes a deceleration of 15 kph for Normal Mode.
    For initial speed of 20 kph, the event should result in 5 kph, but since current speed cannot drop below
    10 kph, the final speed is 10 kph
     */
    @Test
    public void testChangeOfSpeedforNormalModeforSlipperyRoadEvent() {
        NEW_SPEED = normalEvent.getNewSpeed(5);
        normalMode.changeSpeed(NEW_SPEED, 5);
        assertEquals(10, normalMode.CURRENT_SPEED);
    }

    /* First Slippery Road Event is applied, which causes deceleration of 15,
    since speed cannot drop below 10, the resulting speed is 10 kph.
    Slippery Road Clear can only be applied if there was a Slippery Road event applied before,
    thus results in acceleration of 15, which results in final speed of 25kph. */
    @Test
    public void testChangeOfSpeedforNormalModeforSlipperyRoadClearEvent() {
        normalMode.changeSpeed(normalEvent.getNewSpeed(5),5);
        NEW_SPEED = normalEvent.getNewSpeed(6);
        normalMode.changeSpeed(NEW_SPEED,6);
        assertEquals(25,normalMode.CURRENT_SPEED);
    }

    /* Slippery Road Clear Event cannot be applied without a prior Slippery Road Event, if it is given as input
    There is not acceleration, and the speed remains same(20 kph).
    */
    @Test
    public void NoChangeOfSpeedforNormalModeforSlipperyRoadClearEventWithoutSlipperyRoadEvent() {
        NEW_SPEED = normalEvent.getNewSpeed(6);
        normalMode.changeSpeed(NEW_SPEED,6);
        assertEquals(20,normalMode.CURRENT_SPEED);
    }

    /* When a Emergency Turbo event is applied, in Normal mode, an acceleration fo 20 kph is applied,
    thus resulting in speed of 40kph
     */
    @Test
    public void testChangeOfSpeedforNormalModeforTurboEvent() {
        NEW_SPEED = normalEvent.getNewSpeed(7);
        normalMode.changeSpeed(NEW_SPEED,7);
        assertEquals(40,normalMode.CURRENT_SPEED);
    }

    /* Emergency Turbo cannot be applied if Slippery Road Event was applied before
     */
    @Test
    public void NoChangeOfSpeedforNormalModeforTurboEventIfSlipperyRoadEventWasAppliedBefore() {
        normalMode.changeSpeed(normalEvent.getNewSpeed(5),5);
        NEW_SPEED = normalEvent.getNewSpeed(7);
        normalMode.changeSpeed(NEW_SPEED,7);
        assertEquals(10,normalMode.CURRENT_SPEED);
    }

    /* Emergency Turbo can be applied only once, thus when applied first time, resulted in acceleration of 20kph
    for Normal Mode, when applied the second time, there was not change in speed and resulting speed is 40 kph.
     */
    @Test
    public void NoChangeOfSpeedforNormalModeforTurboEventIfTurboEventWasAppliedBefore() {
        normalMode.changeSpeed(normalEvent.getNewSpeed(7),7);
        NEW_SPEED = normalEvent.getNewSpeed(7);
        normalMode.changeSpeed(NEW_SPEED,7);
        assertEquals(40,normalMode.CURRENT_SPEED);
    }

    /* When Speed Limit X event is applied in Normal Mode, the current speed is set to that Speed Limit.
    Does not work for speed limit for more than 99 kph.
     */
    @Test
    public void testChangeSpeedforNormalModeforSpeedLimitEvent() {
        NEW_SPEED = normalEvent.getNewSpeed(98);
        normalMode.changeSpeed(NEW_SPEED, 98);
        assertEquals(98, normalMode.CURRENT_SPEED);
    }

    @Test
    public void testNoChangeOfSpeedForEvents8() {
        NEW_SPEED = normalEvent.getNewSpeed(8);
        normalMode.changeSpeed(NEW_SPEED,8);
        assertEquals(20, normalMode.CURRENT_SPEED);
    }

    @Test
    public void testNoChangeOfSpeedForEvents9() {
        NEW_SPEED = normalEvent.getNewSpeed(9);
        normalMode.changeSpeed(NEW_SPEED,9);
        assertEquals(20, normalMode.CURRENT_SPEED);
    }

    @Test
    public void speedCannotDropBelow10() {
        NEW_SPEED = normalEvent.getNewSpeed(10);
        normalMode.changeSpeed(NEW_SPEED,10);
        NEW_SPEED = normalEvent.getNewSpeed(5);
        normalMode.changeSpeed(NEW_SPEED,5);
        assertEquals(10, normalMode.CURRENT_SPEED);
    }
}
