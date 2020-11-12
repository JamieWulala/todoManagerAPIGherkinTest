Feature: Remove project
  user wants to remove a project and its tasks within

  Background:
    Given The server is running

  Scenario: remove a project with a few tasks in it
    Given there is a project and it has a few tasks
    When I remove the project
    Then the project should be removed
    And The tasks within the project should be removed as well

  Scenario: remove an empty project
    Given there is a project thats has no task
    When I remove the project
    Then the project should be removed

  Scenario: remove a non-existng project
    Given there is a project id that doesn't exist
    When I remove the project
    Then it should return some error saying the project id does not exist
