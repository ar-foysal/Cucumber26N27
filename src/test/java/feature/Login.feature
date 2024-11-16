Feature: Login Feature

  Scenario: User should able to login with valid credentials
    Given  User should be on the login screen
    When User enter valid username and password
    And User click on the login button
    Then User should be see the log out button
    And User should be navigate to the inventory page
    But User should not be on the login page

