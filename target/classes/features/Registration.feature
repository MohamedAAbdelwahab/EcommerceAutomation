Feature: User could register
  #Test Scenario
  Scenario: User could register with valid data
    Given user opens browser To Register
    And user navigates to registration page
    When user enter valid data
    And user click on register
    #Expected result
    Then user could register successfully
