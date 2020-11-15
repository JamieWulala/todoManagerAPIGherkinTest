package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import static org.hamcrest.Matchers.equalTo;

public class s01 extends ApiTest {

    //Category IDs
    private static String categoryHighId;
    private static String categoryMediumId;
    private static String categoryLowId;
    //Todo ID
    private static String todoId;

    @Given("^There are HIGH, MEDIUM and LOW priority categories$")
    public void there_are_high_medium_and_low_priority_categories() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyCategoryHigh = "{\n" +
                "  \"title\": \"HIGH\"\n" +
                "}";
        categoryHighId =
            given().
                contentType("application/json").
                body(requestBodyCategoryHigh).
            when().
                post("/categories").
            then().
                statusCode(201).
            extract().
                jsonPath().getString("id");

        String requestBodyCategoryMedium = "{\n" +
                "  \"title\": \"MEDIUM\"\n" +
                "}";
        categoryMediumId =
            given().
                contentType("application/json").
                body(requestBodyCategoryMedium).
            when().
                post("/categories").
            then().
                statusCode(201).
            extract().
                jsonPath().getString("id");

        String requestBodyCategoryLow = "{\n" +
                "  \"title\": \"LOW\"\n" +
                "}";
        categoryLowId =
            given().
                contentType("application/json").
                body(requestBodyCategoryLow).
            when().
                post("/categories").
            then().
                statusCode(201).
            extract().
                jsonPath().getString("id");
    }

    @When("^I request to change Todo 'Assignment1' to HIGH priority category$")
    public void i_request_to_change_todo_assignment1_to_high_priority_category() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        //ensure todo is not in Low category
        when().
            delete("/todos/{todoId}/categories/{categoryId}", todoId, categoryLowId);

        String requestBodyTodoHighCategory = String.format("{\n" +
                "  \"id\": \"%s\"\n" +
                "}", categoryHighId);
        given().
            contentType("application/json").
            body(requestBodyTodoHighCategory).
        when().
            post("/todos/{id}/categories", todoId);
    }

    @Then("^The Todo 'Assignment1' should be in HIGH priority category$")
    public void the_todo_assignment1_should_be_in_high_priority_category() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/todos/{id}", todoId).
        then().
            statusCode(200).
            body("todos.get(0).categories.get(0).id", equalTo(categoryHighId));
    }

    @Then("^I should receive an error message$")
    public void i_should_receive_an_error_message() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/todos/{id}", todoId).
        then().
            statusCode(404);
    }

    @And("^A Todo named 'Assignment1' has no priority category$")
    public void a_todo_named_assignment1_has_no_priority_category() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyTodo = "{\n" +
                "  \"title\": \"Assignment1\"\n" +
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

    @And("^A Todo named 'Assignment1' is in LOW priority category$")
    public void a_todo_named_assignment1_is_in_low_priority_category() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyTodo = "{\n" +
                "  \"title\": \"Assignment1\"\n" +
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

        String requestBodyTodoLowCategory = String.format("{\n" +
                "  \"id\": \"%s\"\n" +
                "}", categoryLowId);
        given().
            contentType("application/json").
                body(requestBodyTodoLowCategory).
            when().
                post("/todos/{id}/categories", todoId).
            then().
                statusCode(201);
    }

    @And("^There is no Todo named 'Assignment1' in the system$")
    public void there_is_no_todo_named_assignment1_in_the_system() throws Throwable {
        todoId = "10000";
    }

}
