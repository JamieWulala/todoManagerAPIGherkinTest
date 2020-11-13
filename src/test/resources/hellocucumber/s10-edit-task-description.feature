Feature: edit a task description

  Scenario: Edit a task description that does not have description
    Given There is task with no description
    When I edit the task description with some text
    Then The task should have some description

  Scenario: Edit a task description that already has description
    Given There is task with description
    When I edit the task description with some text
    Then The task should have the new description

  Scenario: Edit a non-existing task description  (error flow)
    Given There is a task id that does not exist
    When I edit the description of this non-existing task id
    Then I should receive an error message for editing non-exsiting task