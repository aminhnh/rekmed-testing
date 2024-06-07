Feature: Login
  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When user enters valid credentials
    Then the user should be redirected to the beranda page