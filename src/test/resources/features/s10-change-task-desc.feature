Feature: Change Priority

As a student, I want to adjust the priority of a task, to help better manage my time.

  Background:
    Given The server is running

  Scenario: Change Description of Task (Normal Flow)
    Given A Todo named 'Assignment10' exists
    When I request to change Assignment10’s description to "New Description"
    Then description of Assignment10 should be changed to "New Description"
    And I shutdown the server


  Scenario: Change Description of Task to empty string (Alternate Flow)
    Given A Todo named 'Assignment10' exists
    When I request to change Assignment10’s description to empty string
    Then description of Assignment10 should be changed to empty string
    And I shutdown the server


  Scenario: Change Description of a Task that does not exist (Error Flow)
    Given A Todo named 'Assignment10' does NOT exist
    When I request to change Assignment10’s description to "New Description"
    Then I should receive an error message regarding the missing todo
    And I shutdown the server

