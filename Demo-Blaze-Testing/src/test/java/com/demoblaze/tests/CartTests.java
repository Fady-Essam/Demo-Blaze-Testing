package com.demoblaze.tests;

import com.demoblaze.pages.CartPage;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.ProductPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartTests extends BaseTest {
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private final String TEST_PRODUCT = "Samsung galaxy s6";

    @BeforeClass
    public void initializePages() {
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void testAddToCart() {
        homePage.selectProduct(TEST_PRODUCT);  // Pass product name parameter
        productPage.addToCart();

        // Add explicit wait for alert
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.alertIsPresent()).accept();

        cartPage.navigateToCart();
        softAssert.assertEquals(cartPage.getTotalPrice(), "360", "Price mismatch");
    }

    @Test(dependsOnMethods = "testAddToCart")
    public void testRemoveFromCart() {
        cartPage.deleteItem();
        // Check for "0" instead of empty string
        softAssert.assertEquals(cartPage.getTotalPrice(), "0", "Item not removed");
    }
}