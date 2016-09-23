Feature: Posting and reading a message

  Background:
    Given "Alice" sends message "I love the weather today"
    And "Bob" sends message "Damn! We lost!"
    And "Bob" sends message "Good game though"


  Scenario: Messages posted by Alice can be viewed.
    When the user reads the timeline of "Alice"
    Then the system responds with
      """
      I love the weather today (5 minutes ago)
      """

  Scenario: Messages posted by Alice can be viewed.
    When the user reads the timeline of "Bob"
    Then the system responds with
      """
      Good game though. (1 minute ago)
      Damn! We lost! (2 minutes ago)
      """