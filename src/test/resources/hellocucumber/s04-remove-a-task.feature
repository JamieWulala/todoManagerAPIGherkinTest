Feature: Remove A Task

  As a student, I remove an unnecessary task from my course to do list, so I can forget about it.

  Background:
    Given The server is running

  Scenario: Remove a Done Task (Normal Flow)

    Given There is a Todo named 'Assignment4' has 'true' doneStatus
    When I request to remove the Todo named 'Assignment4'
    Then The Todo named 'Assignment4' is removed
    And I shutdown the server

  Scenario: Remove a Not Done Task (Alternate Flow)

    Given There is a Todo named 'Assignment4' has 'false' doneStatus
    When I request to remove the Todo named 'Assignment4'
    Then The Todo named 'Assignment4' is removed
    And I shutdown the server

  Scenario:  Remove a Task That Does Not Exist (Error Flow)

    Given There is no Todo named 'Assignment4' in the system
    When I request to remove the Todo named 'Assignment4'
    Then I should receive an error message for 'Assignment4' does not exist
    And I shutdown the server