/* Scenario: Verify user is able to add products in cart and confirm the purchase
1. Open the url in Chrome browser - "https://www.saucedemo.com/"
2. Maximize the window
3. Enter username - ""standard_user""
4. Enter password - ""secret_sauce""
5. Click on 'Login' button
6. Verify Swag Labs home page is displayed
7. Select product - Sauce Labs Backpack
8. Click on 'Add to cart' button
9. Click on 'Back to products' button
10. Select product - Sauce Labs Bike Light
11. Click on 'Add to cart' button
12. Click on 'Cart icon' Bucket configuration
13. Scroll page down to 'Checkout' button
14. Click on 'Checkout' button
15. Enter 'First Name'
16. Enter 'Last Name'
17. Enter 'Zip/Postal Code'
18. Scroll page down to 'Continue' button
19. Click 'Continue' button
20. Scroll page down to 'Finish' button
19. Click 'Finish' button
20. Verify the 'Confirmation' message
21. Click 'Back Home' button
22. Navigate to 'Open Menu' button on left top corner
23. Click on 'Open Menu' button on left top corner
24. Click on 'Logout' button
25. Logged out successfully
26. Add wait for 5 secs after each step */

package org.uiTest.swaglabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pom.swablabs.SwagLabsActions;
import org.testng.Assert;
import org.testng.annotations.*;

public class SwagLabsTest {

    WebDriver driver;
    SwagLabsActions actions;

    @BeforeTest
    public void setUp() {
// System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        actions = new SwagLabsActions(driver);
    }

    @Test(priority = 1)
    public void openUrl() throws InterruptedException {
        actions.openUrl("https://www.saucedemo.com/");
        actions.wait(2);
    }

    @Test(priority = 2)
    public void maximizeWindow() throws InterruptedException {
        actions.maximizeWindow();
    }

    @Test(priority = 3)
    public void enterUsername() throws InterruptedException {
        actions.enterUsername("standard_user");
        actions.wait(2);
    }

    @Test(priority = 4)
    public void enterPassword() throws InterruptedException {
        actions.enterPassword("secret_sauce");
        actions.wait(2);
    }

    @Test(priority = 5)
    public void clickLoginButton() throws InterruptedException {
        actions.clickLoginButton();
        actions.wait(5);
    }

    @Test(priority = 6)
    public void verifySwagLabsHomePage() throws InterruptedException {
        actions.verifySwagLabsHomePage();
        actions.wait(2);
    }

    @Test(priority = 7)
    public void selectProductBackpack() throws InterruptedException {
        actions.selectProductBackpack();
        actions.wait(2);
    }

    @Test(priority = 8)
    public void clickAddToCart1() throws InterruptedException {
        actions.clickAddToCart();
        actions.wait(2);
    }

    @Test(priority = 9)
    public void clickBackToProducts() throws InterruptedException {
        actions.clickBackToProducts();
        actions.wait(2);
    }

    @Test(priority = 10)
    public void selectProductBikeLight() throws InterruptedException {
        actions.selectProductBikeLight();
        actions.wait(2);
    }

    @Test(priority = 11)
    public void clickAddToCart2() throws InterruptedException {
        actions.clickAddToCart();
        actions.wait(2);
    }

    @Test(priority = 12)
    public void clickCartIcon() throws InterruptedException {
        actions.clickCartIcon();
        actions.wait(2);
    }

    @Test(priority = 13)
    public void scrollPageDown() throws InterruptedException {
        actions.scrollPageDownToCheckoutButton();
        actions.wait(2);
    }

    @Test(priority = 14)
    public void clickCheckout() throws InterruptedException {
        actions.clickCheckout();
        actions.wait(2);
    }

    @Test(priority = 15)
    public void enterFirstName() throws InterruptedException {
        actions.enterFirstName("John");
        actions.wait(2);
    }

    @Test(priority = 16)
    public void enterLastName() throws InterruptedException {
        actions.enterLastName("Doe");
        actions.wait(2);
    }

    @Test(priority = 17)
    public void enterPostalCode() throws InterruptedException {
        actions.enterPostalCode("12345");
        actions.wait(2);
    }

    @Test(priority = 18)
    public void scrollPageDown2() throws InterruptedException {
        actions.scrollPageDown();
        actions.wait(2);
    }

    @Test(priority = 19)
    public void clickContinueButton() throws InterruptedException {
        actions.clickContinueButton();
        actions.wait(2);
    }

    @Test(priority = 20)
    public void scrollPageDown3() throws InterruptedException {
        actions.scrollPageDown();
        actions.wait(2);
    }

    @Test(priority = 21)
    public void clickFinishButton() throws InterruptedException {
        actions.clickFinishButton();
        actions.wait(2);
    }

    @Test(priority = 22)
    public void verifyConfirmationMessage() throws InterruptedException {
        actions.verifyConfirmationMessage();
        actions.wait(2);
    }

    @Test(priority = 23)
    public void clickBackHomeButton() throws InterruptedException {
        actions.clickBackHomeButton();
        actions.wait(5);
    }

    @Test(priority = 24)
    public void clickOpenMenu() throws InterruptedException {
        actions.clickOpenMenu();
        actions.wait(3);
    }

    @Test(priority = 25)
    public void clickLogout() throws InterruptedException {
        actions.clickLogout();
        actions.wait(2);
    }

    @Test(priority = 26)
    public void verifyLogout() throws InterruptedException {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
