package Cucumber;


import com.here.Event.SportEvent;

import com.here.Mode.SportMode;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class ScenarioIgnore {
    private SportMode sportMode;
    private SportEvent sportEvent;
    int newSpeed;

    @Given("^Driving Mode is Sport$")
    public void driving_Mode_is_Sport() {
        sportMode = new SportMode();
        sportEvent = new SportEvent();
    }

    @When("^Set Input Event followed consecutively same set Input Event$")
    public void set_Input_Event_followed_consecutively_same_set_Input_Event()  {
        newSpeed = sportEvent.getNewSpeed(1);
        sportMode.changeSpeed(newSpeed,1);
        newSpeed = sportEvent.getNewSpeed(1);
        sportMode.changeSpeed(newSpeed,1);

    }

    @Then("^We should see no change in Speed when the second set Event was applied$")
    public void we_should_see_no_change_in_Speed()  {
        assertEquals(15,sportMode.CURRENT_SPEED);
    }

}
