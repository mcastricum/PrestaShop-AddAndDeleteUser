Feature: Purchase product


  Scenario: Purchase 5 Hummingbird Printed Sweaters

    Given User logged in to Presta Shop

    When User chooses Hummingbird Printed Sweater

    And Check if discounted properly

    And User selects size L

    And User takes 5 pcs

    And User adds to cart

    And User goes to checkout

    And User confirms address

    And User confirms delivery option

    And User confirms payment method

    And User confirms purchase

   Then Purchase complete and shows YOUR ORDER IS CONFIRMED

    And User checks orders

    And User finds new order

