package com.example.registrationapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewUsersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<User> userList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);

        loadUserData();

        UserAdapter userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }

    private void loadUserData() {
        Cursor cursor = databaseHelper.getAllUsers();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id= cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD));

                userList.add(new User(id,name, email, password));
            }
            cursor.close();
        }
    }
}
