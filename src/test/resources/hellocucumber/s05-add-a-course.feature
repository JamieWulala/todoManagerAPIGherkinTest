Feature: Add A Course (Project)

  As a student, I create a to do list for a new class I am taking, so I can manage course work.

  Background:
    Given The server is running

  Scenario: Add a Project With Title (Normal Flow)

    When I request to add a Project named 'ECSE 429'
    Then A Project named 'ECSE 429' should be in the system
    And I shutdown the server

  Scenario: Add a Project With Title And Description (Alternate Flow)

    When I request to add a Project named 'ECSE 429' with 'For Assignments' description
    Then A Project named 'ECSE 429' with 'For Assignments' description should be in the system
    And I shutdown the server

  Scenario:  Add a Task Without Title But With Description (Error Flow)

    When I request to add a Project without title and with 'For Assignments' description
    Then I should receive an error message for adding a Project without title
    And I shutdown the server