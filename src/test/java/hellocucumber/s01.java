package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;

import java.io.InputStream;

import static io.restassured.RestAssured.when;
import static org.junit.Assert.*;

public class s01 extends ApiTest {

    @Given("There is a valid endpoint")
    public void there_is_a_valid_endpoint() {
        System.out.println("=============^^^^^^^^^^^+++++++++++================");
        setup();
    }

    @Given("there is a task with no priority category")
    public void there_is_a_task_with_no_priority_category() {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/categories").
        then().
            statusCode(200);
    }

    @Given("there are priority categories as HIGH, MEDIUM, and LOW")
    public void there_are_priority_categories_as_high_medium_and_low() {
        RestAssured.baseURI = "http://localhost:4567";
        when().
                get("/categories").
                then().
                statusCode(200);
    }
    @When("I change the category to HIGH")
    public void i_change_the_category_to_high() {
        RestAssured.baseURI = "http://localhost:4567";
        when().
                get("/categories").
                then().
                statusCode(200);
    }
    @When("select HIGH")
    public void select_high() {
        RestAssured.baseURI = "http://localhost:4567";
        when().
                get("/categories").
                then().
                statusCode(200);
    }
    @Then("The task should be categorized as HIGH")
    public void the_task_should_be_categorized_as_high() {
        RestAssured.baseURI = "http://localhost:4567";
        when().
                get("/categories").
                then().
                statusCode(200);
    }

    @Given("there is a task with HIGH priority")
    public void there_is_a_task_with_high_priority() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I change the category to LOW")
    public void i_change_the_category_to_low() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("The task should be categorized as LOW")
    public void the_task_should_be_categorized_as_low() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("close server")
    public void close_server() {
        // Write code here that turns the phrase above into concrete actions
    }
}
