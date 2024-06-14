Feature: Patient Registration
  Scenario: Successful registration with "simpanTambahButton" button
    Given the user is on the patient registration page
    When the user enters valid patient information
    And the user clicks the "simpanTambahButton" button
    Then the user should see a confirmation message
