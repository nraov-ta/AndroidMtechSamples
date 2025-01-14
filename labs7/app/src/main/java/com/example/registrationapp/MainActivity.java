package com.example.registrationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText;
    private Button registerButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // Register button listener
        registerButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateInputs(name, email, password)) {
                boolean isInserted = databaseHelper.addUser(name, email, password);
                if (isInserted) {
                    Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button viewUsersButton = findViewById(R.id.viewUsersButton);
        viewUsersButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewUsersActivity.class);
            startActivity(intent);
        });
    }

    // Validate inputs
    private boolean validateInputs(String name, String email, String password) {
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Clear input fields after successful registration
    private void clearFields() {
        nameEditText.setText("");
        emailEditText.setText("");
        passwordEditText.setText("");
    }
}
