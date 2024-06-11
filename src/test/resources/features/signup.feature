Feature: Sign Up
  Scenario: Successfull sign up with valid credentials
    Given the user is on the sign up page
    When user enters valid register credentials
    Then the user should be redirected to the beranda page2
