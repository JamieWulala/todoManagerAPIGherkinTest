Feature: Categorize priority
  User wants to categorize tasks a HIGH, MEDIUM or LOW

  Scenario: There is a task that is not categorized
    Given there is a task with no priority category
    And there are priority categories as HIGH, MEDIUM, and LOW
    When I change the category to HIGH
    Then The task should be categorized as HIGH