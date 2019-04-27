package Cucumber;

import com.here.Event.NormalEvent;
import com.here.Event.SafeEvent;
import com.here.Event.SportEvent;
import com.here.Mode.NormalMode;
import com.here.Mode.SafeMode;
import com.here.Mode.SportMode;
import com.here.Validator;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;


public class ScenarioInvalid {
    private SafeMode safeMode;
    private SafeEvent safeEvent;
    private Validator validator = new Validator();
    boolean isValid = true;

    @Given("^Driving Mode is Safe$")
    public void driving_Mode_is_Safe()  {
        safeMode = new SafeMode();
        safeEvent = new SafeEvent();
    }

    @When("^InputEvent is greater than 100$")
    public void inputevent_is_greater_than_100() {
        isValid = validator.isValid(101);

    }

    @Then("^We should see Invalid output$")
    public void we_should_see_Invalid_output()  {
        assertFalse("Invalid", isValid);
    }


}
