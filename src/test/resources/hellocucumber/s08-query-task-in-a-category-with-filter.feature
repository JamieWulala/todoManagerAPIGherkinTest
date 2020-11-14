Feature: query task in a category with filter
  a user want to see their incomplete tasks in certain category

  Background:
    Given The server is running

  Scenario: There are some urgent and incomplete tasks
    Given There are some urgent and incomplete tasks
    When I select view incomplete task with category `urgent`
    Then task in urgent category that are incomplete should be shown

  Scenario: There is no task in urgent category that are incomplete
    Given There is no task in urgent category that are incomplete
    When I select view incomplete task with category `urgent`
    Then no task with category urgent should be shown

  Scenario: category urgent does not exist (error flow)
    Given There are some incomplete tasks but not in category urgent
    When I select view incomplete task with category `urgent`
    Then I should receive an error message for category does not exist