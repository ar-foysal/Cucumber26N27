Feature: Login Feature

  Background:
    Given  User should be on the login screen
  @login @sanity
  Scenario: User should able to login with valid credentials
    When User enter valid username and password
    And User click on the login button
    Then User should be see the log out button
    And User should be navigate to the inventory page
    But User should not be on the login page

  @login
  Scenario: User should not be able to login with an invalid username
    When User enter "shobuj@yopmail.com1" on the username filed
    And User enter "shobuj123" on the password filed
    And User click on the login button
    Then User should see "Your email or password is incorrect!" error message
  @login @invalid_login
  Scenario: User should not be able to login with an invalid password
    When User enter "shobuj@yopmail.com" on the username filed
    And User enter "secret_s" on the password filed
    And User click on the login button
    Then User should see "Your email or password is incorrect!" error message
  @login @invalid_login
  Scenario: User should not be able to login without username
    When User enter "shobuj123" on the password filed
    And User click on the login button
    Then User should see "Please fill out this field." validation message for username
  @login @invalid_login
  Scenario: User should not be able to login without password
    When User enter "shobuj@yopmail.com" on the username filed
    And User click on the login button
    Then User should see "Please fill out this field." validation message for password
  @login @invalid_login
  Scenario: User should not be able to login without username and validation_msg
    When User click on the login button
    Then User should see "Please fill out this field." validation message for username

  Scenario Outline: User should not be able to login with invalid credentials
    When User enter <username> on the username filed
    And User enter <password> on the password filed
    And User click on the login button
    Then User should see <error_msg> error message
    Then User should see <validation_msg_username> validation message for username
    And  User should see <validation_msg_password> validation message for password
    Examples:
      | username            | password  | error_msg                            |validation_msg_username      |validation_msg_password      |
      |"shobuj@yopmail.com1"|"shobuj123"|"Your email or password is incorrect!"|""                           |""                           |
      |"shobuj@yopmail.com" |"shobu222" |"Your email or password is incorrect!"|""                           |""                           |
      |""                   |"shobuj123"|""                                    |"Please fill in this field." |""                           |
      |"shobuj@yopmail.com" |""         |""                                    |""                           |"Please fill in this field."|
      |""                   |""         |""                                    |"Please fill in this field." |""                          |
