Feature: Remove A Todo List For A Class Project

  As a student, I remove a to do list for a class which I am no longer taking, to declutter my schedule.

  Background:
    Given The server is running

  Scenario: Remove a Project With a Few Tasks in It (Normal Flow)
    Given there is a project and it has a few tasks
    When I remove the project
    Then the project should be removed
    And The tasks within the project should be removed as well
    And I shutdown the server

  Scenario: Remove an Empty Project (Alternate Flow)
    Given there is a project thats has no task
    When I remove the project
    Then the project should be removed
    And I shutdown the server

  Scenario: Remove a Non-Existing Project (Error Flow)
    Given there is a project id that doesn't exist
    When I remove the project
    Then it should return some error saying the project id does not exist
    And I shutdown the server
