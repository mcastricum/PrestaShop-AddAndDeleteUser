Feature: Add new address after login


  Scenario Outline: Add new address

    Given User logged in to CodersLab shop

    When User goes to UserAddressesPage

#    And User creates new address

    And User adds and saves new <alias>, <address>, <city>, <postcode>, <country> and <phone>

    Then User gets "Address successfully added!"

    And User deletes address

    Then User deleted address and gets "Address successfully deleted!"

    Examples:
    |alias|address  |city     |postcode|country|phone    |
    |dom  |Prosta 1 |Warszawa |00-000  |United Kingdom |123456789|

