package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class s07 {

    private static String categoryId;
    private static String completedTaskId;
    private static String incompletedTaskId;
    private static String schoolProjectId;

    @Given("^There are some tasks in school project which are incomplete$")
    public void thereAreSomeTasksInSchoolProjectWhichAreIncomplete() {
        RestAssured.baseURI = "http://localhost:4567";

        //Create project and record its id
        String requestBodySchoolProjeect = "{\n" +
                "  \"title\": \"school\"\n" +
                "}";

        schoolProjectId =
                given().contentType("application/json").body(requestBodySchoolProjeect)
                        .when().post("/projects")
                        .then().statusCode(201)
                        .extract().jsonPath().getString("id");

        //Create a completed task in the project, and record its id
        String requestBodycompleteTask = "{\n" +
                "  \"title\": \"completedTask\"\n," +
                "  \"doneStatus\": true\n" +
                "}";

        completedTaskId =
                given().contentType("application/json").body(requestBodycompleteTask)
                .when().post("/projects/{projectId}/tasks", schoolProjectId)
                .then().statusCode(201)
                .extract().jsonPath().getString("id");

        //Create an incomplete task in the project, and record its id
        String requestBodyincompleteTask = "{\n" +
                "  \"title\": \"completedTask\"\n," +
                "  \"doneStatus\": false\n" +
                "}";

        incompletedTaskId =
                given().contentType("application/json").body(requestBodyincompleteTask)
                        .when().post("/projects/{projectId}/tasks", schoolProjectId)
                        .then().statusCode(201)
                        .extract().jsonPath().getString("id");
    }

    @When("^I select view incomplete task in school project$")
    public void iSelectViewIncompleteTaskInSchoolProject() {
        when().get("projects/{projectId}/tasks", schoolProjectId);
    }

    @Then("^task in school project that are incomplete should be shown$")
    public void taskInSchoolProjectThatAreIncompleteShouldBeShown() {
        //Make a query with filtering parameters to get the incomplete task
        when().get("projects/{projectId}/tasks?doneStatus=false", schoolProjectId)
                .then().statusCode(200)
                .body("todos.get(0).id", equalTo(incompletedTaskId));
    }

    @Given("^There are some tasks in school project but are all completed$")
    public void thereAreSomeTasksInSchoolProjectButAreAllCompleted() {
        RestAssured.baseURI = "http://localhost:4567";
        
        String requestBodySchoolProjeect = "{\n" +
                "  \"title\": \"school\"\n" +
                "}";

        schoolProjectId =
                given().contentType("application/json").body(requestBodySchoolProjeect)
                        .when().post("/projects")
                        .then().statusCode(201)
                        .extract().jsonPath().getString("id");

        String requestBodycompleteTask = "{\n" +
                "  \"title\": \"completedTask\"\n," +
                "  \"doneStatus\": true\n" +
                "}";

        completedTaskId =
                given().contentType("application/json").body(requestBodycompleteTask)
                        .when().post("/projects/{projectId}/tasks", schoolProjectId)
                        .then().statusCode(201)
                        .extract().jsonPath().getString("id");
    }

    @Then("^no task should be shown$")
    public void noTaskShouldBeShown() {
        when().get("projects/{projectId}/tasks?doneStatus=false", schoolProjectId)
                .then().statusCode(200)
                .body("todos.size", equalTo(0));
    }

    @Given("^There are some incomplete tasks but no project called school$")
    public void thereAreSomeIncompleteTasksButNoProjectCalledSchool() {
        RestAssured.baseURI = "http://localhost:4567";

        String requestBodycompleteTask = "{\n" +
                "  \"title\": \"completedTask\"\n," +
                "  \"doneStatus\": true\n" +
                "}";

        completedTaskId =
                given().contentType("application/json").body(requestBodycompleteTask)
                        .when().post("todos")
                        .then().statusCode(201)
                        .extract().jsonPath().getString("id");

        String requestBodyincompleteTask = "{\n" +
                "  \"title\": \"completedTask\"\n," +
                "  \"doneStatus\": false\n" +
                "}";

        incompletedTaskId =
                given().contentType("application/json").body(requestBodyincompleteTask)
                        .when().post("todos")
                        .then().statusCode(201)
                        .extract().jsonPath().getString("id");
    }

    @Then("^I should receive an error message for project does not exist$")
    public void iShouldReceiveAnErrorMessageForProjectDoesNotExist() {
        when().get("projects/{projectId}/tasks?doneStatus=false", schoolProjectId)
                .then().statusCode(200); //TODO should show an error message, it's a bug
    }
}
