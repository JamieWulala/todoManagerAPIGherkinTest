package hellocucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.when;

public class s02 extends ApiTest {

    @When("I add a task")
    public void i_add_a_task() {
        // Write code here that turns the phrase above into concrete actions
        RestAssured.baseURI = "http://localhost:4567";
        when().
                get("/categories").
                then().
                statusCode(200);
    }

    @Then("there should be a task")
    public void there_should_be_a_task() {
        // Write code here that turns the phrase above into concrete actions
        RestAssured.baseURI = "http://localhost:4567";
        when().
                get("/categories").
                then().
                statusCode(200);
    }
}
