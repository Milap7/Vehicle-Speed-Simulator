package com.here;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.here.Event.NormalEvent;
import com.here.Event.SafeEvent;
import com.here.Event.SportEvent;
import com.here.Factory.EventFactory;
import com.here.Factory.Factory;
import com.here.Factory.ModeFactory;
import com.here.Mode.NormalMode;
import com.here.Mode.SafeMode;
import com.here.Mode.SportMode;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple Driver.
 */
public class DriverTest
{
    /**
     * Rigorous Test :-)
     */
    private Validator validator;
    private Factory modeFactory;
    private Factory eventFactory;
    @Before
    public void setup() {
        validator = new Validator();
    }

    /* Test over the implementation of correct returned Mode object */
    @Test
    public void shouldInitializeNormalMode() {
        modeFactory = new ModeFactory("NORMAL");
        assertTrue(modeFactory.getMode() instanceof NormalMode);
    }

    @Test
    public void shouldInitializeSafeMode() {
        modeFactory = new ModeFactory("SAFE");
        assertTrue(modeFactory.getMode() instanceof SafeMode);
    }

    @Test
    public void shouldInitializeSportMode() {
        modeFactory = new ModeFactory("SPORT");
        assertTrue(modeFactory.getMode() instanceof SportMode);
    }

    /* Test the validator logic*/
    @Test
    public void shouldReturnFalseforInvalidEvent() {
        assertFalse(validator.isValid(101));
    }

    /* Test the validator logic*/
    @Test
    public void shouldReturnTrueforValidEvent() {
        assertTrue(validator.isValid(1));
    }

    /* Test over the implementation of correct returned Event object*/
    @Test
    public void shouldInitializeNormalEvent() {
        eventFactory = new EventFactory("NORMAL");
        assertTrue(eventFactory.getEvent() instanceof NormalEvent);
    }

    @Test
    public void shouldInitializeSportEvent() {
        eventFactory = new EventFactory("SPORT");
        assertTrue(eventFactory.getEvent() instanceof SportEvent);
    }

    @Test
    public void shouldInitializeSafeEvent() {
        eventFactory = new EventFactory("SAFE");
        assertTrue(eventFactory.getEvent() instanceof SafeEvent);
    }
}
