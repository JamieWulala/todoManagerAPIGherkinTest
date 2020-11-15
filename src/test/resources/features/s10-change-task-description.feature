Feature: Change Task Description

  As a student, I want to change a task description, to better represent the work to do.

  Background:
    Given The server is running

  Scenario: Change Description of Task (Normal Flow)
    Given A Todo named 'Assignment10' exists
    When I request to change Assignment10’s description to "New Description"
    Then description of Assignment10 should be changed to "New Description"
    And I shutdown the server

  Scenario: Change Description of Task to Empty String (Alternate Flow)
    Given A Todo named 'Assignment10' exists
    When I request to change Assignment10’s description to empty string
    Then description of Assignment10 should be changed to empty string
    And I shutdown the server

  Scenario: Change Description of a Task That Does Not Exist (Error Flow)
    Given A Todo named 'Assignment10' does NOT exist
    When I request to change Assignment10’s description to "New Description"
    Then I should receive an error message regarding the missing todo
    And I shutdown the server
