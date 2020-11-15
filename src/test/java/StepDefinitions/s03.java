package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class s03 extends ApiTest {

    //Todo ID
    private static String todoId;

    @Given("^There is a Todo named 'Assignment3' has 'false' doneStatus and empty description$")
    public void there_is_a_todo_named_assignment3_has_false_donestatus_and_empty_description() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyTodo = "{\n" +
                "  \"title\": \"Assignment3\"\n" +
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

    @Given("^There is no Todo named 'Assignment3' in the system$")
    public void there_is_no_todo_named_assignment3_in_the_system() throws Throwable {
        todoId = "10000";
    }

    @When("^I request to change Todo 'Assignment3' to 'true' doneStatus$")
    public void i_request_to_change_todo_assignment3_to_true_donestatus() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyTodo = "{\n" +
                "  \"doneStatus\": true\n" +
                "}";
        given().
            contentType("application/json").
            body(requestBodyTodo).
        when().
            post("/todos/{id}", todoId);
    }

    @When("^I request to change Todo 'Assignment3' to 'true' doneStatus and 'This is done' description$")
    public void i_request_to_change_todo_assignment3_to_true_donestatus_and_this_is_done_description() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyTodo = "{\n" +
                "  \"doneStatus\": true,\n" +
                "  \"description\": \"This is done\"\n" +
                "}";
        given().
            contentType("application/json").
            body(requestBodyTodo).
        when().
            post("/todos/{id}", todoId).
        then().
            statusCode(200).
            body("doneStatus", equalTo("true")).
            body("description", equalTo("This is done"));
    }

    @Then("^The Todo 'Assignment3' should have 'true' doneStatus$")
    public void the_todo_assignment3_should_have_true_donestatus() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/todos/{id}", todoId).
        then().
            statusCode(200).
            body("todos.get(0).doneStatus", equalTo("true"));
    }

    @Then("^The Todo 'Assignment3' should have 'true' doneStatus and 'This is done' description$")
    public void the_todo_assignment3_should_have_true_donestatus_and_this_is_done_description() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/todos/{id}", todoId).
        then().
            statusCode(200).
            body("todos.get(0).doneStatus", equalTo("true"));
    }

    @Then("^I should receive an error message for 'Assignment3' does not exist$")
    public void i_should_receive_an_error_message_for_assignment3_does_not_exist() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/todos/{id}", todoId).
        then().
            statusCode(404);
    }

}
