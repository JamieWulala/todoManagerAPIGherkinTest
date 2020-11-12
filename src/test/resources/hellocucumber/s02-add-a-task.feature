Feature: Add a task
  user wants to add task

  Scenario: Add a task
    Given there is a valid endpoint
    When I add a task
    Then there should be a task
    And close server