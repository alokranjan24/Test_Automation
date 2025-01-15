package org.pom.swablabs;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SwagLabsActions {
    WebDriver driver;
    SwagLabsElements elements;

    public SwagLabsActions(WebDriver driver) {
        this.driver = driver;
        elements = new SwagLabsElements(driver);
    }

    public void login(String username, String password) throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        elements.username().sendKeys(username);
        Thread.sleep(2000);
        elements.password().sendKeys(password);
        Thread.sleep(2000);
        elements.loginButton().click();
        Thread.sleep(2000);
        Assert.assertTrue(elements.swagLabsHeader().isDisplayed());
    }

    public void addToCart() throws InterruptedException {
        elements.productBackpack().click();
        Thread.sleep(2000);
        elements.addToCart().click();
        Thread.sleep(2000);
        elements.backToProducts().click();
        Thread.sleep(2000);
        elements.productBikeLight().click();
        Thread.sleep(2000);
        elements.addToCart().click();
        Thread.sleep(2000);
        elements.cart().click();
        Thread.sleep(2000);
        elements.checkout().click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"));
    }

    public void checkout(String firstName, String lastName, String zipCode) throws InterruptedException {
        elements.firstName().sendKeys(firstName);
        Thread.sleep(2000);
        elements.lastName().sendKeys(lastName);
        Thread.sleep(2000);
        elements.zipCode().sendKeys(zipCode);
        Thread.sleep(2000);
        elements.btnContinue().click();
        Thread.sleep(2000);
        elements.finish().click();
        Thread.sleep(2000);
        Assert.assertTrue(elements.confirmationMessage().isDisplayed());
        elements.backHomeButton().click();
        Thread.sleep(2000);
    }

    public void logout() throws InterruptedException {
        elements.openMenu().click();
        Thread.sleep(2000);
        elements.logout().click();
//Assert.assertTrue(driver.getCurrentUrl().contains("index.html"));
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void enterUsername(String username) {
        elements.username().sendKeys(username);
    }

    public void enterPassword(String password) {
        elements.password().sendKeys(password);
    }

    public void clickLoginButton() {
        elements.loginButton().click();
    }

    public void verifySwagLabsHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(elements.swagLabsHeader()));
    }

    public void selectProductBackpack() {
        elements.productBackpack().click();
    }

    public void selectProductBikeLight() {
        elements.productBikeLight().click();
    }

    public void clickAddToCart() {
        elements.addToCart().click();
    }

    public void clickBackToProducts() {
        elements.backToProducts().click();
    }

    public void clickCartIcon() {
        elements.cartIcon().click();
    }

    public void scrollPageDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
    }

    public void scrollPageDownToCheckoutButton() {

// genericUI.scrollPageDownToElement(elements.getCheckoutButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elements.getCheckoutButton());
    }

    public void clickCheckout() {
        elements.getCheckoutButton().click();
    }

    public void enterFirstName(String firstName) {
        elements.firstName().sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        elements.lastName().sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        elements.postalCode().sendKeys(postalCode);
    }

    public void clickContinueButton() {
        elements.continueButton().click();
    }

    public void clickFinishButton() {
        elements.finishButton().click();
    }

    public void verifyConfirmationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(elements.confirmationMessage()));
    }

    public void clickBackHomeButton() {
        elements.backHomeButton().click();
    }

    public void clickOpenMenu() {
        elements.openMenu().click();
    }

    public void clickLogout() {
        elements.logout().click();
    }

    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}