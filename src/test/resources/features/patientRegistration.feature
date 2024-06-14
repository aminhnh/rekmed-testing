Feature: Patient Registration
  Background: Successful login with valid credentials
    Given the user is on the login page
    When user enters valid credentials
    Then the user should be redirected to the beranda page
  Scenario: Successful registration with "simpanTambahButton" button
    Given the user is on the patient registration page
    When the user enters valid patient information
    And the user clicks the "simpanTambahButton" button
    Then the user should see a confirmation message