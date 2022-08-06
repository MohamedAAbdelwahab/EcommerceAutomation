Feature: user should be able add different products to his compare list
  #Test Scenario
  Scenario: User add products to his compare list
    Given user opens browser add products to his compare list
    And user is logged in to add products to his compare list
    And select  category to compare from
    When user click on add to compare list on two products
    #Expected result
    Then user should see that there is a product in his compare list and success message