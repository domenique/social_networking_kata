Feature: Hello World

  Scenario: A world says hello
    Given the system knows my name is "Domenique Tilleuil"
    When I say hi to the system
    Then the system responds with "Hello, Domenique Tilleuil"