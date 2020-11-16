package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import static org.hamcrest.Matchers.equalTo;

public class s09 extends ApiTest {

    //Category IDs
    private static String categoryHighId;
    private static String categoryMediumId;
    private static String categoryLowId;

    private static String assignment9ID;

    @Given("There are HIGH, MEDIUM, and LOW priority categories")
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

    @Given("a Todo with name {string} exists")
    public void a_todo_with_name_exists(String todoName) {
        //Make a new TODO named assignment 9
        String todoTitle = todoName;
        String requestBodyAssignment9 = String.format("{\n" +
                "  \"title\": \"%s\"\n" +
                "}", todoName);

        assignment9ID =
            given().
                contentType("application/json").
                body(requestBodyAssignment9).
            when().
                post("/todos").
            then().
                statusCode(201).
            extract().
                jsonPath().getString("id");


    }

    @Given("a Todo with name {string} does NOT exist")
        public void a_todo_with_name_does_not_exist(String todoName) {
            // Change the TODO id to a value that doesn't exist
            assignment9ID = "9999";
    }

    @Given("a Todo with name {string} is in LOW priority category")
    public void a_todo_with_name_is_in_low_priority_category(String todoName) {
        // Create a relationship between your todo and low priority
         RestAssured.baseURI = "http://localhost:4567";
         String requestBodyTodoLowCategory = String.format("{\n" +
                "  \"id\": \"%s\"\n" +
                "}", categoryLowId);
         given().
            contentType("application/json").
            body(requestBodyTodoLowCategory).
         when().
            post("/todos/{id}/categories", assignment9ID).
         then().
            statusCode(201);

    }
    @When("I request to change Todo {string} to HIGH priority category using category id")
    public void i_request_to_change_todo_to_high_priority_category_using_category_id(String todoName) {
        RestAssured.baseURI = "http://localhost:4567";
        //Delete the low priority relationship
        when().
            delete("/todos/{todoId}/categories/{categoryId}", assignment9ID, categoryLowId);
        // Create a relationship between your todo and high priority
        String requestBodyTodoHighCategory = String.format("{\n" +
                "  \"id\": \"%s\"\n" +
                "}", categoryHighId);
        given().
            contentType("application/json").
            body(requestBodyTodoHighCategory).
        when().
            post("/todos/{id}/categories", assignment9ID);

    }

    @When("I request to change Todo {string} to HIGH priority category using category id and description")
    public void i_request_to_change_todo_to_high_priority_category_using_category_id_and_description(String todoName) {
        RestAssured.baseURI = "http://localhost:4567";
        //Delete the low priority relationship
        when().
            delete("/todos/{todoId}/categories/{categoryId}", assignment9ID, categoryLowId);
        // Create a relationship between your todo and high priority
         String requestBodyTodoHighCategory = String.format("{\n" +
                "  \"id\": \"%s\",\n" +
                 "  \"description\": \"%s\"\n" +
                "}", categoryHighId, "Some description");
         given().
            contentType("application/json").
            body(requestBodyTodoHighCategory).
         when().
            post("/todos/{id}/categories", assignment9ID);


    }
    @Then("the Todo {string} should be in HIGH priority category")
    public void the_todo_should_be_in_high_priority_category(String todoName) {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/todos/{id}", assignment9ID).
        then().
            statusCode(200).
            body("todos.get(0).categories.get(0).id", equalTo(categoryHighId));

    }

    @Then("I should receive an error message regarding the missing Todo")
    public void i_should_receive_an_error_message_regarding_the_missing_todo() {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/todos/{id}", assignment9ID).
        then().
            statusCode(404);
    }
}
