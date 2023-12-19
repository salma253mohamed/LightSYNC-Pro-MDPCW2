package com.example.myapplication;

import android.view.View;

import com.example.myapplication.Login;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class LoginTest {

    @Test
    public void loginButtonClick_shouldAuthenticateUser() {
        Login loginActivity = Robolectric.setupActivity(Login.class);
        assertNotNull(loginActivity);

        loginActivity.username.setText("test@example.com");
        loginActivity.password.setText("test123456");

        // Mocking a button click
        loginActivity.logInBtn.performClick();

        // Validate that the authentication logic is triggered
        assertNotNull(Shadows.shadowOf(loginActivity).getNextStartedActivity());
    }

    @Test
    public void validateEmail_shouldReturnTrueForValidEmail() {
        Login loginActivity = Robolectric.setupActivity(Login.class);
        assertNotNull(loginActivity);

        boolean isValidEmail = loginActivity.validateEmail("test@example.com");
        assertEquals(true, isValidEmail);
    }

    @Test
    public void validateEmail_shouldReturnFalseForInvalidEmail() {
        Login loginActivity = Robolectric.setupActivity(Login.class);
        assertNotNull(loginActivity);

        boolean isValidEmail = loginActivity.validateEmail("invalidemail");
        assertEquals(false, isValidEmail);
    }

    @Test
    public void validatePassword_shouldReturnTrueForValidPassword() {
        Login loginActivity = Robolectric.setupActivity(Login.class);
        assertNotNull(loginActivity);

        boolean isValidPassword = loginActivity.validatePassword("test123");
        assertEquals(true, isValidPassword);
    }

    @Test
    public void validatePassword_shouldReturnFalseForEmptyPassword() {
        Login loginActivity = Robolectric.setupActivity(Login.class);
        assertNotNull(loginActivity);

        boolean isValidPassword = loginActivity.validatePassword("");
        assertEquals(false, isValidPassword);
    }
}
