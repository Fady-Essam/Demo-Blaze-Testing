package com.demoblaze.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage extends BasePage {
    // Locators
    private By signupLink = By.id("signin2");
    private By usernameField = By.id("sign-username");
    private By passwordField = By.id("sign-password");
    private By signupButton = By.xpath("//button[text()='Sign up']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    // Navigate to signup modal
    public void navigateToSignup() {
        click(signupLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    }

    // Fill and submit signup form
    public void signup(String username, String password) {
        sendKeys(usernameField, username);
        sendKeys(passwordField, password);
        click(signupButton);
    }

    // Handle success/error alert
    public String handleSignupAlert() {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (TimeoutException e) {
            return null; // No alert found
        }
    }
}