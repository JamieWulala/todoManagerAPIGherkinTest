Feature: Adjust Task Priority

  As a student, I want to adjust the priority of a task, to help better manage my time.

  Background:
    Given The server is running

  Scenario: Change Priority Level of Task Without Modifying Description (Normal Flow)
    Given There are HIGH, MEDIUM, and LOW priority categories
    And a Todo with name "Assignment9" exists
    And a Todo with name "Assignment9" is in LOW priority category
    When I request to change Todo "Assignment9" to HIGH priority category using category id
    Then the Todo "Assignment9" should be in HIGH priority category
    And I shutdown the server

  Scenario: Change Priority Level of Task and Modify Description (Alternate Flow)
    Given There are HIGH, MEDIUM, and LOW priority categories
    And a Todo with name "Assignment9" exists
    And a Todo with name "Assignment9" is in LOW priority category
    When I request to change Todo "Assignment9" to HIGH priority category using category id and description
    Then the Todo "Assignment9" should be in HIGH priority category
    And I shutdown the server

  Scenario: Change Priority Level of Non-existing Task (Error Flow)
    Given There are HIGH, MEDIUM, and LOW priority categories
    And a Todo with name "Assignment9" does NOT exist
    When I request to change Todo "Assignment9" to HIGH priority category using category id
    Then I should receive an error message regarding the missing Todo
    And I shutdown the server
