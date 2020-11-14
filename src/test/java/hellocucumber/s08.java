package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class s08 {

    @Given("There are some urgent and incomplete tasks")
    public void thereAreSomeUrgentAndIncompleteTasks() {
    }

    @When("I select view incomplete task with category `urgent`")
    public void iSelectViewIncompleteTaskWithCategoryUrgent() {
    }

    @Then("task in urgent category that are incomplete should be shown")
    public void taskInUrgentCategoryThatAreIncompleteShouldBeShown() {
    }

    @Given("There is no task in urgent category that are incomplete")
    public void thereIsNoTaskInUrgentCategoryThatAreIncomplete() {
    }

    @Then("no task with category urgent should be shown")
    public void noTaskWithCategoryUrgentShouldBeShown() {
    }

    @Given("There are some incomplete tasks but not in category urgent")
    public void thereAreSomeIncompleteTasksButNotInCategoryUrgent() {
    }

    @Then("I should receive an error message for category does not exist")
    public void iShouldReceiveAnErrorMessageForCategoryDoesNotExist() {
    }
}
