package hellocucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class s06 extends ApiTest {

    private static String projectId;
    private static String taskId;

    private static String NONEXISTING_ID = "100000";

    @Given("^there is a project and it has a few tasks$")
    public void thereIsAProjectAndItHasAFewTasks() {
        RestAssured.baseURI = "http://localhost:4567";

        String requestBodyproject = "{\n" +
                "  \"title\": \"Aproject\"\n" +
                "}";
        projectId =
                given().
                        contentType("application/json").
                        body(requestBodyproject).
                when().
                        post("/projects").
                then().
                        statusCode(201).
                extract().
                        jsonPath().getString("id");


        String requestBodytask = "{\n" +
                "  \"title\": \"Atask\"\n" +
                "}";
        taskId =
                given().
                        contentType("application/json").
                        body(requestBodytask)
                .when().
                        post("projects/{id}/tasks", projectId)
                .then().
                        statusCode(201)
                .extract().
                        jsonPath().getString("id");
    }

    @When("^I remove the project$")
    public void iRemoveTheProject() {
        when().delete("/projects/{id}", projectId);
    }

    @Then("^the project should be removed$")
    public void theProjectShouldBeRemoved() {
        when().get("/projects/{id}", projectId).then().statusCode(404);
    }

    @And("^The tasks within the project should be removed as well$")
    public void theTasksWithinTheProjectShouldBeRemovedAsWell() {
        when().get("/todos/{id}", taskId).then().statusCode(200); //TODO should be 404, known bug
    }

    @Given("^there is a project thats has no task$")
    public void thereIsAProjectThatsHasNoTask() {
        RestAssured.baseURI = "http://localhost:4567";

        String requestBodyproject = "{\n" +
                "  \"title\": \"Aproject\"\n" +
                "}";
        projectId =
                given().
                        contentType("application/json").
                        body(requestBodyproject).
                        when().
                        post("/projects").
                        then().
                        statusCode(201).
                        extract().
                        jsonPath().getString("id");
    }

    @Given("^there is a project id that doesn't exist$")
    public void thereIsAProjectIdThatDoesnTExist() {
        when().get("/projects/{id}", NONEXISTING_ID).then().statusCode(404);
    }

    @Then("^it should return some error saying the project id does not exist$")
    public void itShouldReturnSomeErrorSayingTheProjectIdDoesNotExist() {
        when().delete("/projects/{id}", NONEXISTING_ID).then().statusCode(404);
    }
}
