package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class s02 extends ApiTest {

    //Todo ID
    private static String todoId;

    @When("^I request to add a Todo named 'Assignment2' without description$")
    public void i_request_to_add_a_todo_named_assignment2_without_description() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyTodo = "{\n" +
                "  \"title\": \"Assignment2\"\n" +
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

    @When("^I request to add a Todo named 'Assignment2' with description 'ECSE'$")
    public void i_request_to_add_a_todo_named_assignment2_with_description_ecse() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyTodo = "{\n" +
                "  \"title\": \"Assignment2\",\n" +
                "  \"description\": \"ECSE\"\n" +
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

    @When("^I request to add a Todo without title but with description 'ECSE'$")
    public void i_request_to_add_a_todo_without_title_but_with_description_ecse() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyTodo = "{\n" +
                "  \"description\": \"ECSE\"\n" +
                "}";

        given().
            contentType("application/json").
            body(requestBodyTodo).
        when().
            post("/todos").
        then().
            statusCode(400).
        extract().
            jsonPath().getString("id");

        todoId = "1000";
    }

    @Then("^A Todo named 'Assignment2' should be in the system$")
    public void a_todo_named_assignment2_should_be_in_the_system() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/todos/{id}", todoId).
        then().
            statusCode(200);
    }

    @Then("^A Todo named 'Assignment2' with description 'ECSE' should be in the system$")
    public void a_todo_named_assignment2_with_description_ecse_should_be_in_the_system() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/todos/{id}", todoId).
        then().
            statusCode(200);
    }

    @Then("^I should receive an error message for adding a todo without title$")
    public void i_should_receive_an_error_message_for_adding_a_todo_without_title() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/todos/{id}", todoId).
        then().
            statusCode(404);
    }
}
