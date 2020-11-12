Feature: Categorize priority
  User wants to categorize tasks a HIGH, MEDIUM or LOW

  Background:
    Given There is a valid endpoint

  Scenario: There is a task that is not categorized
    Given there is a task with no priority category
    And there are priority categories as HIGH, MEDIUM, and LOW
    When I change the category to HIGH
    Then The task should be categorized as HIGH
    And close server

  Scenario: There is a task that is categorized (alternative flow)
    Given there is a task with HIGH priority
    And there are priority categories as HIGH, MEDIUM, and LOW
    When I change the category to LOW
    Then The task should be categorized as LOW
    And close server