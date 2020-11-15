package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import static org.hamcrest.Matchers.equalTo;

public class SetupShutdown extends ApiTest{

    @Given("^The server is running$")
    public void the_server_is_running() throws Throwable {
        setup();
    }

    @And("^I shutdown the server$")
    public void i_shutdown_the_server() throws Throwable {
        shutdown();
    }
}
