package hellocucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class s06 {

    @Given("^there is a project and it has a few tasks$")
    public void thereIsAProjectAndItHasAFewTasks() {
    }

    @When("^I remove the project$")
    public void iRemoveTheProject() {
    }

    @Then("^the project should be removed$")
    public void theProjectShouldBeRemoved() {
    }

    @And("^The tasks within the project should be removed as well$")
    public void theTasksWithinTheProjectShouldBeRemovedAsWell() {
    }

    @Given("^there is a project thats has no task$")
    public void thereIsAProjectThatsHasNoTask() {
    }

    @Given("^there is a project id that doesn't exist$")
    public void thereIsAProjectIdThatDoesnTExist() {
    }

    @Then("^it should return some error saying the project id does not exist$")
    public void itShouldReturnSomeErrorSayingTheProjectIdDoesNotExist() {
    }
}
