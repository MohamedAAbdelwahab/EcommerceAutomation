Feature: user should be able add different products to his wishlist
  #Test Scenario
  Scenario: User add products to his wishlist
    Given user opens browser add products to his wishlist
    And user is logged in to add products to his wishlist
    And select  category
    When user click on add to wishlist on one product
    #Expected result
    Then user should see that there is a product in his wishlist and success message