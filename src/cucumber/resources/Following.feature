Feature: Following an other user

  Background:
    Given "Alice" sends message "I love the weather today."
    And 180 seconds pass by
    And "Bob" sends message "Damn! We lost!"
    And 60 seconds pass by
    And "Bob" sends message "Good game though."
    And 60 seconds pass by
    And "Charlie" sends message "I'm in New York today! Anyone wants to have coffee?"

  Scenario: Charlie can subscribe to Alice's timeline.
    Given "Charlie" follows "Alice"
    And 2 seconds pass by
    When the user reads the wall of "Charlie"
    Then the system responds with
    """
    Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)
    Alice - I love the weather today (5 minutes ago)
    """

  Scenario:
    Given "Charlie" follows "Alice"
    And "Charlie" follows "Bob"
    And 15 seconds pass by
    When the user reads the wall of "Charlie"
    Then the system responds with
    """
    Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
    Bob - Good game though. (1 minutes ago)
    Bob - Damn! We lost! (2 minute ago)
    Alice - I love the weather today (5 minutes ago)
    """