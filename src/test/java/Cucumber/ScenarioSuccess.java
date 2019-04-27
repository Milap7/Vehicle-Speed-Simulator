package Cucumber;

import com.here.Event.NormalEvent;
import com.here.Mode.NormalMode;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class ScenarioSuccess {
    private NormalMode normalMode;
    private NormalEvent normalEvent;
    private int newSpeed;


    @Given("^Driving Mode is Normal$")
    public void driving_Mode_is_Normal()  {
        normalMode = new NormalMode();
        normalEvent = new NormalEvent();
    }

    @When("^Input Event is Speed Limit 50$")
    public void InputEvent_is_Speed_Limit_50()  {
        newSpeed = normalEvent.getNewSpeed(50);

    }

    @Then("^We should see the current speed as 50$")
    public void We_should_see_the_current_speed_as_50()  {
        normalMode.changeSpeed(newSpeed, 50);
        assertEquals(50, normalMode.CURRENT_SPEED);

    }

}
