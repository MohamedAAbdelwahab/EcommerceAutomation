Feature: user should be able add different products to his cart
  #Test Scenario
  Scenario: User add products to his cart
    Given user opens browser add products to his cart
    And user is logged in to add products to his cart
    And select subcategory from category
    When user click on add to cart on one product
    #Expected result
    Then user should see that there is a product in his cart and success message