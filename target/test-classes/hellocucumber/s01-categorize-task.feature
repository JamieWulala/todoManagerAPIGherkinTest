Feature: Categorize A Task

  As a student, I categorize tasks as HIGH, MEDIUM or LOW priority, so I can better manage my time.

  Background:
    Given The server is running

  Scenario: Categorize a Task without Priority Level (Normal Flow)

    Given There are HIGH, MEDIUM and LOW priority categories
    And A Todo named 'Assignment1' has no priority category
    When I request to change Todo 'Assignment1' to HIGH priority category
    Then I should receive a confirmation message
    And I shutdown the server

  Scenario: Categorize a Task to Another Priority Level (Alternate Flow)

    Given There are HIGH, MEDIUM and LOW priority categories
    And A Todo named 'Assignment1' is in LOW priority category
    When I request to change Todo 'Assignment1' to HIGH priority category
    Then I should receive a confirmation message
    And I shutdown the server

  Scenario: Categorize a Task that does not exist (Error Flow)

    Given There are HIGH, MEDIUM and LOW priority categories
    And There is no Todo named 'Assignment1' in the system
    When I request to change Todo 'Assignment1' to HIGH priority category
    Then I should receive an error message
    And I shutdown the server