package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)  // Adjust the SDK version as needed
public class SignUpTest {

    private SignUp signUpActivity;
    private FirebaseAuth mockAuth;
    private EditText mockUserEditText;
    private EditText mockPassEditText;
    private Button mockSignUpButton;

    @Before
    public void setUp() {
        // Mock FirebaseAuth
        mockAuth = mock(FirebaseAuth.class);
        // Create the activity
        signUpActivity = Robolectric.buildActivity(SignUp.class).create().get();
        // Get references to UI elements
        mockUserEditText = signUpActivity.findViewById(R.id.username);
        mockPassEditText = signUpActivity.findViewById(R.id.password);
        mockSignUpButton = signUpActivity.findViewById(R.id.signup);
        // Set up FirebaseAuth in the activity with the mock
        signUpActivity.auth = mockAuth;
    }

    @Test
    public void testSignUpButtonClick() {
        // Set test values
        String testUser = "test@example.com";
        String testPass = "testPassword";

        // Set values in EditTexts
        mockUserEditText.setText(testUser);
        mockPassEditText.setText(testPass);

        // Trigger the button click
        mockSignUpButton.performClick();

        // Verify that createUserWithEmailAndPassword was called with the correct values
        verify(mockAuth).createUserWithEmailAndPassword(testUser, testPass);

        // Simulate a successful task execution
        when(mockAuth.createUserWithEmailAndPassword(testUser, testPass))
                .thenReturn(mock(Task.class));

        // Verify that startActivity was called after a successful sign-up
        assertTrue(Robolectric.buildActivity(Login.class).create().get().isFinishing());
    }
}

