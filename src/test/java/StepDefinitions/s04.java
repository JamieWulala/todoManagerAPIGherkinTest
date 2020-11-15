package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class s04 extends ApiTest {

    //Todo ID
    private static String todoId;

    @Given("^There is a Todo named 'Assignment4' has 'true' doneStatus$")
    public void there_is_a_todo_named_assignment4_has_true_donestatus() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyTodo = "{\n" +
                "  \"title\": \"Assignment4\",\n" +
                "  \"doneStatus\": true\n" +
                "}";
        todoId =
            given().
                contentType("application/json").
                body(requestBodyTodo).
            when().
                post("/todos").
            then().
                statusCode(201).
            extract().
                jsonPath().getString("id");
    }

    @Given("^There is a Todo named 'Assignment4' has 'false' doneStatus$")
    public void there_is_a_todo_named_assignment4_has_false_donestatus() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyTodo = "{\n" +
                "  \"title\": \"Assignment4\",\n" +
                "  \"doneStatus\": false\n" +
                "}";
        todoId =
            given().
                contentType("application/json").
                body(requestBodyTodo).
            when().
                post("/todos").
            then().
                statusCode(201).
            extract().
                jsonPath().getString("id");
    }

    @Given("^There is no Todo named 'Assignment4' in the system$")
    public void there_is_no_todo_named_assignment4_in_the_system() throws Throwable {
        todoId = "10000";
    }

    @When("^I request to remove the Todo named 'Assignment4'$")
    public void i_request_to_remove_the_todo_named_assignment4() throws Throwable {
        when().
            delete("/todos/{id}", todoId);
    }

    @Then("^The Todo named 'Assignment4' is removed$")
    public void the_todo_named_assignment4_is_removed() throws Throwable {
        when().
            get("/todos/{id}", todoId).
        then().
            statusCode(404);
    }

    @Then("^I should receive an error message for 'Assignment4' does not exist$")
    public void i_should_receive_an_error_message_for_assignment4_does_not_exist() throws Throwable {
        when().
            get("/todos/{id}", todoId).
        then().
            statusCode(404);
    }

}
