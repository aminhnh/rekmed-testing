Feature: Cashier

  Background:
    Given the user is on the login page
    When user enters valid credentials
    Then the user should be redirected to the beranda page

  Scenario: Successfully process payment
    When User clicks the 'Kasir' menu
    And User clicks the 'Bayar' button
    And User fills in the payment form
    And User clicks the 'Bayar' button in the form
    Then The payment should be successfully processed