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

        EditText nameInput = findViewById(R.id.nameInput);
        Button greetButton = findViewById(R.id.greetButton);
        TextView greetingMessage = findViewById(R.id.greetingMessage);

        greetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                if (!name.isEmpty()) {
                    greetingMessage.setText("Hello " + name);
                } else {
                    greetingMessage.setText("Hello! Please enter your name.");
                }
            }
        });
    }
}
