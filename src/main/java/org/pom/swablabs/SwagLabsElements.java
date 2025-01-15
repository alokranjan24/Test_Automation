package org.pom.swablabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SwagLabsElements {
    WebDriver driver;

    public SwagLabsElements(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement username() {
        return driver.findElement(By.id("user-name"));
    }

    public WebElement password() {
        return driver.findElement(By.id("password"));
    }

    public WebElement loginButton() {
        return driver.findElement(By.id("login-button"));
    }

    public WebElement swagLabsHeader() {
        return driver.findElement(By.xpath("//*[@id='header_container']/div[1]/div[2]/div"));
    }

    public WebElement openMenu() {
        return driver.findElement(By.id("react-burger-menu-btn"));
    }

    public WebElement logout() {
        return driver.findElement(By.id("logout_sidebar_link"));
    }

    public WebElement productBackpack() {
        return driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
    }

    public WebElement productBikeLight() {
        return driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']"));
    }

    public WebElement addToCart() {
        return driver.findElement(By.xpath("//button[text()='Add to cart']"));
    }

    public WebElement backToProducts() {
        return driver.findElement(By.xpath("//button[text()='Back to products']"));
    }

    public WebElement addToCartBackpack() {
        return driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/following::button[1]"));
    }

    public WebElement addToCartBikeLight() {
        return driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']/following::button[1]"));
    }

    public WebElement cart() {
        return driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
    }

    public WebElement checkout() {
        return driver.findElement(By.xpath("//button[text()='Checkout']"));
    }

    public WebElement firstName() {
        return driver.findElement(By.id("first-name"));
    }

    public WebElement lastName() {
        return driver.findElement(By.id("last-name"));
    }

    public WebElement zipCode() {
        return driver.findElement(By.id("postal-code"));
    }

    public WebElement btnContinue() {
        return driver.findElement(By.xpath("//*[@id=\'continue\']"));
    }

    public WebElement finish() {
        return driver.findElement(By.xpath("//*[@id=\'finish\']"));
    }

    public WebElement confirmationMessage() {
        return driver.findElement(By.xpath("//h2[text()='Thank you for your order!']"));
    }

    public WebElement getCheckoutButton() {
        return driver.findElement(By.xpath("//button[text()='Checkout']"));
    }

    public WebElement backHomeButton() {
        return driver.findElement(By.xpath("//button[text()='Back Home']"));
    }

    public WebElement cartIcon() {
        return driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
    }

    public WebElement postalCode() {
        return driver.findElement(By.id("postal-code"));
    }

    public WebElement continueButton() {
        return driver.findElement(By.xpath("//*[@id=\'continue\']"));
    }

    public WebElement finishButton() {
        return driver.findElement(By.xpath("//*[@id=\'finish\']"));
    }


}