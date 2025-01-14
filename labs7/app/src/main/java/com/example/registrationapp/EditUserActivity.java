package com.example.registrationapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditUserActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText;
    private Button saveButton;
    private DatabaseHelper databaseHelper;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        saveButton = findViewById(R.id.saveButton);

        databaseHelper = new DatabaseHelper(this);
        userId = getIntent().getIntExtra("userId", -1);

        // Load user data
        User user = databaseHelper.getUserById(userId);
        if (user != null) {
            nameEditText.setText(user.getName());
            emailEditText.setText(user.getEmail());
            passwordEditText.setText(user.getPassword());
        }

        // Save changes
        saveButton.setOnClickListener(v -> {
            String newName = nameEditText.getText().toString();
            String newEmail = emailEditText.getText().toString();
            String newPassword = passwordEditText.getText().toString();

            databaseHelper.updateUser(userId, newName, newEmail, newPassword);
            Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
