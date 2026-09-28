Feature: Login Functionality
  As a standard user
  I want to log into the SauceDemo website
  So that I can purchase items

  Scenario: Successful login with valid credentials
    Given I am on the SauceDemo login page
    When I enter my valid username and password
    And I click the login button
    Then I should be redirected to the products page