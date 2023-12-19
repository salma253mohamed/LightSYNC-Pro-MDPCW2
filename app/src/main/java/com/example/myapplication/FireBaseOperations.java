package com.example.myapplication;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireBaseOperations {
    private DatabaseReference myNodeReference;

    public void FirebaseOperations() {
        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Reference to your node (replace "myNode" with your desired node name)
        myNodeReference = database.getReference("users");
    }
    public void addUser(String userId, String fullName, String userName, List<String> itemList) {
        UserHelperClass user = new UserHelperClass(fullName, userName, itemList);
        myNodeReference.child(userId).setValue(user);
    }
    public void saveUserData(String name, String username, List<String> dataArray) {
        // Create a unique userId (replace this with your actual logic)
        String userId = myNodeReference.push().getKey();

        // Create a Map to represent the user data
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("username", username);
        userData.put("dataArray", dataArray);

        // Save data to the "users" node with the unique userId
        myNodeReference.child(userId).setValue(userData);
    }

    public void checkUsernameExists(final String username, final UsernameCheckCallback callback) {
        myNodeReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Username exists
                    callback.onUsernameCheckResult(true);
                } else {
                    // Username does not exist
                    callback.onUsernameCheckResult(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
                callback.onUsernameCheckResult(false); // Assume username doesn't exist in case of error
            }
        });
    }

    // Callback interface for handling the result of the username check
    public interface UsernameCheckCallback {
        void onUsernameCheckResult(boolean usernameExists);
    }

    // Method to retrieve user data from the database
    public void getUser(String userId) {
        myNodeReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    UserHelperClass user = dataSnapshot.getValue(UserHelperClass.class);
                    // Do something with the user object
                } else {
                    // User not found
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }
}
