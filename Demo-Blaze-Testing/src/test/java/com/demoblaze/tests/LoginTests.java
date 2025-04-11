package com.demoblaze.tests;

import com.aventstack.extentreports.Status;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.LoginPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {
    private HomePage homePage;
    private LoginPage loginPage;
    private String VALID_PASSWORD = "testpass";
    private By logoutLink = By.id("logout2");

    @BeforeClass
    public void initializePages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        test.log(Status.INFO, "Pages initialized");
    }

    @Test(priority = 1)
    public void testValidLogin() {
        test.log(Status.INFO, "Starting valid login test");

        try {
            test.log(Status.INFO, "Navigating to login page");
            homePage.navigateToLogin();

            test.log(Status.INFO, "Performing login with credentials");
            loginPage.performLogin(BaseTest.userName, VALID_PASSWORD);

            test.log(Status.INFO, "Handling login alert");
            String alertText = handleLoginAlert();

            test.log(Status.INFO, "Verifying login success");
            softAssert.assertTrue(alertText != null && alertText.contains("Welcome"),
                    "Missing welcome alert. Actual alert text: " + alertText);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            softAssert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).isDisplayed(),
                    "Logout link not visible after login");

            test.log(Status.PASS, "Login test completed successfully");
        } catch (Exception e) {
            test.log(Status.FAIL, "Exception occurred: " + e.getMessage());
            throw e;
        }
    }

    private String handleLoginAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String text = alert.getText();
            alert.accept();
            return text;
        } catch (TimeoutException e) {
            test.log(Status.WARNING, "No login alert appeared");
            softAssert.fail("No login alert appeared");
            return null;
        }
    }
}