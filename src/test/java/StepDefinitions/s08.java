package StepDefinitions;
import java.lang.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class s08 {
    private static String categoryHighId;
    private static String categoryLowId;
    private static String incompleteUrgentTaskId;
    private static String completedUrgentTaskId;
    private static String incompleteLowTaskId;


    @Given("There are HIGH and LOW priority categories")
    public void there_are_high_and_low_priority_categories() {
        RestAssured.baseURI = "http://localhost:4567";
        //Create categories for high and low priority
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

    @Given("There are some urgent and incomplete tasks")
    public void thereAreSomeUrgentAndIncompleteTasks() {
        //Create a completed task and record its id
        String requestBodycompleteTask = "{\n" +
                "  \"title\": \"completedTask\"\n," +
                "  \"doneStatus\": true\n" +
                "}";

        completedUrgentTaskId =
            given().
                contentType("application/json").
                body(requestBodycompleteTask).
            when().
                post("/todos").
            then().
                statusCode(201).
            extract().
                jsonPath().getString("id");

        //Create an incomplete task and record its id
        String requestBodyincompleteTask = "{\n" +
                "  \"title\": \"incompleteTask\"\n," +
                "  \"doneStatus\": false\n" +
                "}";

        incompleteUrgentTaskId =
            given().
                contentType("application/json").
                body(requestBodyincompleteTask).
            when().
                post("/todos").
            then().
                statusCode(201).
            extract().
                jsonPath().getString("id");

         //Now put both of them under the HIGH priority category
        String requestBodyTodoUrgentCompleted = String.format("{\n" +
                "  \"id\": \"%s\"\n" +
                "}", completedUrgentTaskId);
        given().
            contentType("application/json").
            body(requestBodyTodoUrgentCompleted).
        when().
            post("/categories/{id}/todos", categoryHighId).
        then().
            statusCode(201);

        String requestBodyTodoUrgentIncomplete = String.format("{\n" +
                "  \"id\": \"%s\"\n" +
                "}", incompleteUrgentTaskId);
        given().
            contentType("application/json").
            body(requestBodyTodoUrgentIncomplete).
        when().
            post("/categories/{id}/todos", categoryHighId).
        then().
            statusCode(201);


        //System.out.printf("\nAssociated both todos with the HIGH category id %s\n" , categoryHighId);

    }


    @When("I select view incomplete task with category `urgent`")
    public void iSelectViewIncompleteTaskWithCategoryUrgent() {
        when().get("categories/{categoryId}/todos?doneStatus=false", categoryHighId);
    }

    @Then("task in urgent category that are incomplete should be shown")
    public void taskInUrgentCategoryThatAreIncompleteShouldBeShown() {
        //Make a query with filtering parameters to get the incomplete task
        when().
            get("categories/{categoryId}/todos?doneStatus=false", categoryHighId).
        then().
            statusCode(200).
            body("todos.get(0).id", equalTo(incompleteUrgentTaskId));

    }

    @Given("There is no task in urgent category that are incomplete")
    public void thereIsNoTaskInUrgentCategoryThatAreIncomplete() {
        //Only add completed task
        String requestBodycompleteTask = "{\n" +
                "  \"title\": \"completedTask\"\n," +
                "  \"doneStatus\": true\n" +
                "}";

        completedUrgentTaskId =
            given().
                contentType("application/json").body(requestBodycompleteTask).
            when().
                post("/todos").
            then().
                statusCode(201).
            extract().
                jsonPath().getString("id");

        //Associate with HIGH category
        String requestBodyTodoUrgentCompleted = String.format("{\n" +
                "  \"id\": \"%s\"\n" +
                "}", completedUrgentTaskId);
        given().
            contentType("application/json").
            body(requestBodyTodoUrgentCompleted).
        when().
            post("/categories/{id}/todos", categoryHighId).
        then().
            statusCode(201);
    }

    @Then("no task with category urgent should be shown")
    public void noTaskWithCategoryUrgentShouldBeShown() {
        //Make a query with filtering parameters to get the incomplete task
        when().
            get("categories/{categoryId}/todos?doneStatus=false", categoryHighId).
        then().
            statusCode(200).
            body("todos.size()", equalTo(0));
    }


    @Given("There is no category for HIGH priority")
    public void there_is_no_category_for_high_priority() {
        categoryHighId = "9999999999";
    }


    @Then("I should receive an error message for category does not exist")
    public void iShouldReceiveAnErrorMessageForCategoryDoesNotExist() {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("categories/{categoryId}", categoryHighId).
        then().
            statusCode(404);

    }
}
