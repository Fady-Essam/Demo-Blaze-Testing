package com.demoblaze.tests;

import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.ProductPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductTests extends BaseTest {
    private final String TEST_PRODUCT = "Samsung galaxy s6";
    private HomePage homePage;
    private ProductPage productPage;

    @BeforeClass
    public void initializePages() {
        homePage = new HomePage(driver);
    }

    @Test
    public void testAddToCart() {
        homePage.selectProduct(TEST_PRODUCT);
        productPage = new ProductPage(driver);
        productPage.addToCart();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }
}