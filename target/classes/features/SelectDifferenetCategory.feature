Feature: user should be able select another category
  #Test Scenario
  Scenario: User select another category
    Given user opens browser to change category
    And user is logged in with valid data
    When hover on category
    And user select sub category
    #Expected result
    Then user navigate to subcategory