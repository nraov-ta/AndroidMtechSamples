package com.example.hellouserapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to UI components
        EditText etName = findViewById(R.id.etName);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        TextView tvGreeting = findViewById(R.id.tvGreeting);

        // Handle button click
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                if (!name.isEmpty()) {
                    tvGreeting.setText("Hello " + name + "!");
                } else {
                    tvGreeting.setText("Hello! Please enter your name.");
                }
            }
        });
    }
}
