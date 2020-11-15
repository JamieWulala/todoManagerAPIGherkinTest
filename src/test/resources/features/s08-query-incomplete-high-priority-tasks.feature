Feature: Query All Incomplete HIGH Priority Tasks From All Classes

  As a student, I query all incomplete HIGH priority tasks from all my classes, to identity my short-term goals.

  Background:
    Given The server is running

  Scenario: There are Incomplete HIGH Priority Tasks (Normal Flow)
    Given There are HIGH and LOW priority categories
    And There are some urgent and incomplete tasks
    When I select view incomplete task with category `urgent`
    Then task in urgent category that are incomplete should be shown
    And I shutdown the server

  Scenario: There is No Incomplete HIGH Priority Tasks (Alternate Flow)
    Given There are HIGH and LOW priority categories
    And There is no task in urgent category that are incomplete
    When I select view incomplete task with category `urgent`
    Then no task with category urgent should be shown
    And I shutdown the server

  Scenario: HIGH Priority Category Does Not Exist (Error Flow)
    Given There is no category for HIGH priority
    When I select view incomplete task with category `urgent`
    Then I should receive an error message for category does not exist
    And I shutdown the server