package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class s07 {

    @Given("^There are some tasks in school project which are incomplete$")
    public void thereAreSomeTasksInSchoolProjectWhichAreIncomplete() {
    }

    @When("^I select view incomplete task in school project$")
    public void iSelectViewIncompleteTaskInSchoolProject() {
    }

    @Then("^task in school project that are incomplete should be shown$")
    public void taskInSchoolProjectThatAreIncompleteShouldBeShown() {
    }

    @Given("^There are some tasks in school project but are all completed$")
    public void thereAreSomeTasksInSchoolProjectButAreAllCompleted() {
    }

    @Then("^no task should be shown$")
    public void noTaskShouldBeShown() {
    }

    @Given("^There are some incomplete tasks but no project called school$")
    public void thereAreSomeIncompleteTasksButNoProjectCalledSchool() {
    }

    @Then("^I should receive an error message for project does not exist$")
    public void iShouldReceiveAnErrorMessageForProjectDoesNotExist() {
    }
}
