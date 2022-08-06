Feature: user should be able select different tag
  #Test Scenario
  Scenario: User select by different tag
    Given user opens browser select by different tag
    And logged in user
    When user choose a category
    And select tag
    #Expected result
    Then user should see the products this tag only