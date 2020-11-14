package hellocucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.*;

import static org.hamcrest.Matchers.equalTo;
public class s10 {
    private static String assignment10ID;

    @Given("A Todo named {string} exists")
    public void a_todo_named_exists(String todoName) {
        //Make a new TODO named Assignment10
        RestAssured.baseURI = "http://localhost:4567";
        String todoTitle = todoName;
        String requestBodyAssignment10 = "{\n" +
                    "  \"title\": \"Assignment10\"\n" +
                    "}";
        assignment10ID =
        given().
                contentType("application/json").
                body(requestBodyAssignment10).
        when().
                post("/todos").
        then().
                statusCode(201).
                extract().
                jsonPath().getString("id");

    }

    @Given("A Todo named {string} does NOT exist")
    public void a_todo_named_does_not_exist(String string) {
        assignment10ID = "99999";

    }

    @When("I request to change Assignment10’s description to {string}")
    public void i_request_to_change_assignment10_s_description_to(String newDesc) {
        RestAssured.baseURI = "http://localhost:4567";

        String requestBodyAssignment10 = String.format("{\n" +
                "  \"description\": \"%s\"\n" +
                "}", newDesc);

        given().
                contentType("application/json").
                body(requestBodyAssignment10).
        when().
                post("/todos/{id}", assignment10ID);


    }

    @When("I request to change Assignment10’s description to empty string")
    public void i_request_to_change_assignment10_s_description_to_empty_string() {
        RestAssured.baseURI = "http://localhost:4567";
        String emptyString = "";
        String requestBodyAssignment10 = String.format("{\n" +
                "  \"description\": \"%s\"\n" +
                "}", emptyString);

        given().
                contentType("application/json").
                body(requestBodyAssignment10).
        when().
                put("/todos/{id}", assignment10ID);

    }

    @Then("description of Assignment10 should be changed to {string}")
    public void description_of_assignment10_should_be_changed_to(String newDesc) {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/todos/{id}", assignment10ID).
        then().
            statusCode(200).
            body("todos.get(0).description", equalTo(newDesc));


    }

    @Then("description of Assignment10 should be changed to empty string")
    public void description_of_assignment10_should_be_changed_to_empty_string() {
        RestAssured.baseURI = "http://localhost:4567";
        String emptyString = "";
        when().
            get("/todos/{id}", assignment10ID).
        then().
            statusCode(200).
            body("todos.get(0).description", equalTo(emptyString));

    }

    @Then("I should receive an error message regarding the missing todo")
    public void i_should_receive_an_error_message_regarding_the_missing_todo() {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/todos/{id}", assignment10ID).
        then().
            statusCode(404);

    }


}
