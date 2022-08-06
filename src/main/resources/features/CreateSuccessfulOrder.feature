Feature: user should be able make successful order
  #Test Scenario
  Scenario: User make successful order
    Given user opens browser to make successful java
    And user is logged in to make successful order
    And select  category to add products from
    And  user click on add to cart
    When user accept terms and condition
    And user click on checkout
    And user enter his billing address
    And user select shipping method
    And user choose his payment method
    And user check his payment info
    And user click on confirm payment
    #Expected result
    Then user see his order number on the screen