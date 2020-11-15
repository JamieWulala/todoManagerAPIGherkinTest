Feature: Query Incomplete Tasks For A Class

  As a student, I query the incomplete tasks for a class I am taking, to help manage my time.

  Background:
    Given The server is running

  Scenario: There are Incomplete Tasks in a Project (Normal Flow)
    Given There are some tasks in school project which are incomplete
    When I select view incomplete task in school project
    Then task in school project that are incomplete should be shown
    And I shutdown the server

  Scenario: There are no Incomplete Tasks in a Project (Alternate Flow)
    Given There are some tasks in school project but are all completed
    When I select view incomplete task in school project
    Then no task should be shown
    And I shutdown the server

  Scenario: Query Tasks for Project Does Not Exist (Error Flow)
    Given There are some incomplete tasks but no project called school
    When I select view incomplete task in school project
    Then I should receive an error message for project does not exist
    And I shutdown the server