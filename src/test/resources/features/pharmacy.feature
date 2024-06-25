Feature: Pharmacy

  Background:
    Given the user is on the login page
    When user enters valid credentials
    Then the user should be redirected to the beranda page

  Scenario: Successfully added patient from pharmacy queue to payment queue
    When User clicks the 'Farmasi' menu
    And User clicks the 'RESEP' button
    And User clicks the 'Tambah Resep' button
    And User fills in the medication data
    And User clicks the 'Selesai' button
    Then The patient's data should be successfully saved and added to the payment queue