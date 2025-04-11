package com.demoblaze.tests;

import com.demoblaze.pages.SignupPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

public class SignupTests extends BaseTest {
    private SignupPage signupPage;
    private static final String PASSWORD = "testpass";
    private String randomUsername;

    @BeforeClass
    public void initializePages() {
        signupPage = new SignupPage(driver);
    }

    // Generate random username method
    private String generateRandomUsername() {
        BaseTest.userName = "user_" + UUID.randomUUID().toString().substring(0, 8);
        return BaseTest.userName;
    }

    @Test(priority = 0)
    public void testUserSignup() {
        BaseTest.userName = generateRandomUsername();
        signupPage.navigateToSignup();
        signupPage.signup(BaseTest.userName, PASSWORD);
        String alertText = signupPage.handleSignupAlert();

        // Use softAssert from BaseTest instead of local instance
        softAssert.assertTrue(alertText != null && alertText.contains("Sign up successful"),
                "Signup failed: " + alertText);
    }
}