Feature: Change Task Status

  As a student, I mark a task as done on my course to do list, so I can track my accomplishments.

  Background:
    Given The server is running

  Scenario: Change Status of a Todo (Normal Flow)

    Given There is a Todo named 'Assignment3' has 'false' doneStatus and empty description
    When I request to change Todo 'Assignment3' to 'true' doneStatus
    Then The Todo 'Assignment3' should have 'true' doneStatus
    And I shutdown the server

  Scenario: Change Status And Description of a Todo (Alternate Flow)

    Given There is a Todo named 'Assignment3' has 'false' doneStatus and empty description
    When I request to change Todo 'Assignment3' to 'true' doneStatus and 'This is done' description
    Then The Todo 'Assignment3' should have 'true' doneStatus and 'This is done' description
    And I shutdown the server

  Scenario:  Change Status of a Todo That Does Not Exist (Error Flow)

    Given There is no Todo named 'Assignment3' in the system
    When I request to change Todo 'Assignment3' to 'true' doneStatus
    Then I should receive an error message for 'Assignment3' does not exist
    And I shutdown the server