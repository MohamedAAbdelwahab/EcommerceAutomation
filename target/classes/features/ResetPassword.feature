Feature: user should be able to reset his password
  #Test Scenario
  Scenario: User reset his password
    Given user opens browser to reset his password
    And user click on forget password button
    When user enter valid email
    And user click on recover button
    #Expected result
    Then user could reset his password
