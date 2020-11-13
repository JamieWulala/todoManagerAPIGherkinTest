Feature: query task in a project with filter
  a user want to see their incomplete tasks for certain project

  Scenario: There are tasks for school that are incomplete
    Given There are some tasks in school project which are incomplete
    When I select view incomplete task in school project
    Then task in school project that are incomplete should be shown

  Scenario: There are no tasks for school that are incomplete
    Given There are some tasks in school project but are all completed
    When I select view incomplete task in school project
    Then no task should be shown

  Scenario: project school does not exist (error flow)
    Given There are some incomplete tasks but no project called school
    When I select view incomplete task in school project
    Then I should receive an error message for project does not exist