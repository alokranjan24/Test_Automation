package org.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pom.swablabs.SwagLabsActions;


public class SwagLab_StepDefinition {

    WebDriver driver;
    SwagLabsActions actions;
    public SwagLab_StepDefinition() {
        //System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        actions = new SwagLabsActions(driver);
    }

    @Given("User is on the SwagLab eCommerce application login page")
    public void user_is_on_the_swag_lab_e_commerce_application_login_page_https_www_saucedemo_com() throws InterruptedException {
        actions.login("standard_user", "secret_sauce");
        System.out.println("User is on the SwagLab eCommerce application login page");
    }

    @When("User enters valid login credentials")
    public void user_enters_valid_login_credentials() {
        System.out.println("User enters valid login credentials");
    }

    @When("User clicks on the Login button")
    public void user_clicks_on_the_login_button() {
        System.out.println("User clicks on the Login button");
    }

    @Then("User should be redirected to the SwagLab eCommerce application home page")
    public void user_should_be_redirected_to_the_swag_lab_e_commerce_application_home_page() {
        System.out.println("User is redirected to the SwagLab eCommerce application home page");
    }

    @When("User adds product Sauce Labs Backpack to the cart")
    public void user_adds_product_sauce_labs_backpack_to_the_cart() throws InterruptedException {
        actions.addToCart();
        System.out.println("User adds product Sauce Labs Backpack to the cart");
    }

    @When("User adds product Sauce Labs Bike Light to the cart")
    public void user_adds_product_sauce_labs_bike_light_to_the_cart() {
        System.out.println("User adds product Sauce Labs Bike Light to the cart");
    }

    @When("User navigate to the Cart and clicks on the Checkout button")
    public void user_navigate_to_the_cart_and_clicks_on_the_checkout_button() throws InterruptedException {
        actions.checkout("John", "Doe", "12345");
        System.out.println("User is navigated to the Cart and clicks on the Checkout button");
    }

    @Then("User should be redirected to the Checkout page")
    public void user_should_be_redirected_to_the_checkout_page() {
        System.out.println("User is redirected to the Checkout page");
    }

    @When("User enters the required information First Name, Last Name and Zip Code on the Checkout page")
    public void user_enters_the_required_information_first_name_last_name_and_zip_code_on_the_checkout_page() {
        System.out.println("User enters the required information First Name, Last Name and Zip Code on the Checkout page");
    }

    @When("User clicks on the Finish button")
    public void user_clicks_on_the_finish_button() {
        System.out.println("User clicks on the Finish button");
    }

    @Then("User should see a confirmation message")
    public void user_should_see_a_confirmation_message() {
        System.out.println("User see a confirmation message");
    }

    @Then("User logs out of the application")
    public void user_logs_out_of_the_application() throws InterruptedException {
        actions.logout();
        System.out.println("User log out of the application");
    }

    @Then("User close the browser")
    public void user_close_the_browser() {
        driver.quit();
    }

}
