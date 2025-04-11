package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private By cartLink = By.id("cartur");
    private By totalPrice = By.id("totalp");
    private By deleteItemLink = By.xpath("//td/a[contains(text(),'Delete')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToCart() {
        click(cartLink);
    }

    public String getTotalPrice() {
        try {
            return getText(totalPrice);
        } catch (NoSuchElementException e) {
            return "0";  // Handle missing total price element
        }
    }

    public void deleteItem() {
        click(deleteItemLink);
    }
    public boolean isCartEmpty() {
        return driver.findElements(totalPrice).isEmpty() || getText(totalPrice).equals("0");
    }
}