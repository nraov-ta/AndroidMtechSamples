package com.example.registrationapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<User> userList;

    public UserAdapter(ArrayList<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.nameTextView.setText("Name: " + user.getName());
        holder.emailTextView.setText("Email: " + user.getEmail());
        holder.passwordTextView.setText("Password: " + user.getPassword());

        // Edit button logic
        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), EditUserActivity.class);
            intent.putExtra("userId", user.getId());
            holder.itemView.getContext().startActivity(intent);
        });

        // Delete button logic
        holder.deleteButton.setOnClickListener(v -> {
            DatabaseHelper dbHelper = new DatabaseHelper(holder.itemView.getContext());
            dbHelper.deleteUser(user.getId());
            userList.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(holder.itemView.getContext(), "User Deleted", Toast.LENGTH_SHORT).show();
        });
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView, passwordTextView;
        Button editButton, deleteButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            passwordTextView = itemView.findViewById(R.id.passwordTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }


}
