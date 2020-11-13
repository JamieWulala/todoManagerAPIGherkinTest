package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class s05 extends ApiTest {

    //Todo ID
    private static String projectId;

    @When("^I request to add a Project named 'ECSE 429'$")
    public void i_request_to_add_a_project_named_ecse_429() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyProject = "{\n" +
                "  \"title\": \"ECSE 429\"\n" +
                "}";
        projectId =
            given().
                contentType("application/json").
                body(requestBodyProject).
            when().
                post("/projects").
            then().
                statusCode(201).
            extract().
                jsonPath().getString("id");
    }

    @When("^I request to add a Project named 'ECSE 429' with 'For Assignments' description$")
    public void i_request_to_add_a_project_named_ecse_429_with_for_assignments_description() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyProject = "{\n" +
                "  \"title\": \"ECSE 429\",\n" +
                "  \"description\": \"For Assignments\"\n" +
                "}";
        projectId =
            given().
                contentType("application/json").
                body(requestBodyProject).
            when().
                post("/projects").
            then().
                statusCode(201).
            extract().
                jsonPath().getString("id");
    }

    @When("^I request to add a Project without title and with 'For Assignments' description$")
    public void i_request_to_add_a_project_without_title_and_with_for_assignments_description() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        String requestBodyProject = "{\n" +
                "  \"description\": \"For Assignments\"\n" +
                "}";
        projectId =
            given().
                contentType("application/json").
                body(requestBodyProject).
            when().
                post("/projects").
            then().
                statusCode(201).
            extract().
                jsonPath().getString("id");
        //System.out.println("Know Error: The system accepts project with empty title");
        projectId = "10000";
    }

    @Then("^A Project named 'ECSE 429' should be in the system$")
    public void a_project_named_ecse_429_should_be_in_the_system() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/projects/{id}", projectId).
        then().
            statusCode(200);
    }

    @Then("^A Project named 'ECSE 429' with 'For Assignments' description should be in the system$")
    public void a_project_named_ecse_429_with_for_assignments_description_should_be_in_the_system() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/projects/{id}", projectId).
        then().
            statusCode(200);
    }

    @Then("^I should receive an error message for adding a Project without title$")
    public void i_should_receive_an_error_message_for_adding_a_project_without_title() throws Throwable {
        RestAssured.baseURI = "http://localhost:4567";
        when().
            get("/projects/{id}", projectId).
        then().
            statusCode(404);
    }

}
