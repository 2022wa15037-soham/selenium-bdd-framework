Feature: User Login Functionality

@positive
  Scenario: Successful login with valid credentials
    Given user is on the login page
    When user enters username "Admin" and password "admin123"
    And user clicks on the login button
    Then user should see the "Dashboard" page


@negetive
  Scenario: Unsuccessful login with invalid credentials
    Given user is on the login page
    When user enters username "Admin" and password "wrongpassword"
    And user clicks on the login button
    And user should see the "Dashboard" page
