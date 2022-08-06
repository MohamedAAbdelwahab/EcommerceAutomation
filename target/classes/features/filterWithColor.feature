Feature: user should be able filter by color
  #Test Scenario
  Scenario: User filter by red color
    Given user opens browser to filter by color
    And user is logged in by valid data
    When user choose an category
    And select filter by red color
    #Expected result
    Then user should see the products with red colors only