package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By loginLink = By.id("login2");
    private By productLink = By.xpath("//a[contains(text(),'Samsung galaxy s6')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLogin() {
        click(loginLink);
    }

    public void selectProduct(String productName) {
        By productLink = By.xpath("//a[contains(text(),'" + productName + "')]");
        click(productLink);
    }
}