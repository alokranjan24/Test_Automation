@SwagLabs
Feature: User purchases products on SwagLab eCommerce application

  @UIDemoSwagLabs
  Scenario: User purchases two products and logs out
    Given User is on the SwagLab eCommerce application login page
    When User enters valid login credentials
    And User clicks on the Login button
    Then User should be redirected to the SwagLab eCommerce application home page
    When User adds product Sauce Labs Backpack to the cart
    And User adds product Sauce Labs Bike Light to the cart
    And User navigate to the Cart and clicks on the Checkout button
    Then User should be redirected to the Checkout page
    When User enters the required information First Name, Last Name and Zip Code on the Checkout page
    And User clicks on the Finish button
    Then User should see a confirmation message
    And User logs out of the application
    And User close the browser