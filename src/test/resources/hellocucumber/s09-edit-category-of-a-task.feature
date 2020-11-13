Feature: edit the category of a task
  a user wants to adjust the priority which is a category

  Scenario: Adjust the category of task which has a different category
    Given there is a task that has category of HIGH
    When I change the category to low
    Then The task priority category should be changed to low

  Scenario: Adjust the category of task which has no category
    Given there is a task that has no priority category
    When I change the category to low
    Then The task category should be changed to low

  Scenario: Adjust the category of task, but the category does not exist (error flow)
    Given there is a task
    When I change the category to a category that doesn't exist
    Then I should receive an error message saying category does not exist

