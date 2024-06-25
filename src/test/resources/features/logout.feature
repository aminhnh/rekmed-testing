Feature: User Logout

  Scenario: User logs out from the account and returns to the login page
    Given the user is logged in
    When the user clicks on "logout" button
    Then the system successfully displays the login page