Feature: user should be able to search for item
  #Test Scenario
  Scenario: User search for item
    Given user opens browser to search for item
    And user is logged in
    When user type item name
    And user click on search
    #Expected result
    Then user could find his item
