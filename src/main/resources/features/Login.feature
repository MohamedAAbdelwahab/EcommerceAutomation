Feature: user should be able to login to the system
  #Test Scenario
  Scenario: User could log in with valid email and password
  Given user opens browser
  And user navigates to login page
  When user enter valid username and password
  And user click on login button
    #Expected result
  Then user could login successfully
