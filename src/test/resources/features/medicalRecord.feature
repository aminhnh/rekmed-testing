Feature: Patient Medical Record
  Background:
    Given the user is logged in
  Scenario: Saving patient examination and check-up data
    Given the user is on the pemeriksaan page
    When the user enters check-up data
    And the user saves the check-up data
    And the user enters examination data
    And the user saves the examination data
    Then the examination and check-up data should be saved successfully
