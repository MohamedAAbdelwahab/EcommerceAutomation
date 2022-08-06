Feature: user should be able to change currency
  #Test Scenario
  Scenario: User change currency
    Given user opens browser to search for item to change currency
    And user is loggedin
    When user enter item name
    And user click on search button
    And User change the currency
    #Expected result
    Then user could see the change of the currency
