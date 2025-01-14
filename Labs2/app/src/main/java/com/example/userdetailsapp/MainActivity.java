package com.example.userdetailsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_relative);

        // Get references to UI components
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etAddress = findViewById(R.id.etAddress);
        EditText etAge = findViewById(R.id.etAge);
        RadioGroup radioGender = findViewById(R.id.radioGender);
        DatePicker dpDob = findViewById(R.id.dpDob);
        Spinner spState = findViewById(R.id.spState);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        TextView tvResult = findViewById(R.id.tvResult);

        // Set up a sample state spinner (modify as needed)
        spState.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Select State", "California", "New York", "Texas", "Florida"}));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve values
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String address = etAddress.getText().toString();
                String age = etAge.getText().toString();
                String gender = ((RadioButton) findViewById(radioGender.getCheckedRadioButtonId())).getText().toString();
                String state = spState.getSelectedItem().toString();
                String dob = dpDob.getDayOfMonth() + "/" + (dpDob.getMonth() + 1) + "/" + dpDob.getYear();

                // Display values below the Submit button
                tvResult.setText(String.format("Username: %s\nPassword: %s\nAddress: %s\nGender: %s\nAge: %s\nDOB: %s\nState: %s",
                        username, password, address, gender, age, dob, state));
            }
        });
    }
}
