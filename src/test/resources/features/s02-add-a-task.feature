Feature: Add A Task

  As a student, I add a task to a course to do list, so I can remember it.

  Background:
    Given The server is running

  Scenario: Add a Task With Title (Normal Flow)

    When I request to add a Todo named 'Assignment2' without description
    Then A Todo named 'Assignment2' should be in the system
    And I shutdown the server

  Scenario: Add a Task With Title And Description (Alternate Flow)

    When I request to add a Todo named 'Assignment2' with description 'ECSE'
    Then A Todo named 'Assignment2' with description 'ECSE' should be in the system
    And I shutdown the server

  Scenario:  Add a Task Without Title (Error Flow)

    When I request to add a Todo without title but with description 'ECSE'
    Then I should receive an error message for adding a todo without title
    And I shutdown the server