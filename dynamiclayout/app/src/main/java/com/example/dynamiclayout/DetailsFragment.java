package com.example.dynamiclayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    private static final String ARG_NAME = "name";
    private static final String ARG_DETAILS = "details";

    public static DetailsFragment newInstance(Candidate candidate) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, candidate.getName());
        args.putString(ARG_DETAILS, candidate.getDetails());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvDetails = view.findViewById(R.id.tvDetails);
        Button btnBack = view.findViewById(R.id.btnBack);

        // Set name and details from arguments
        if (getArguments() != null) {
            tvName.setText(getArguments().getString(ARG_NAME, "Name"));
            tvDetails.setText(getArguments().getString(ARG_DETAILS, "Details"));
        }

        // Back button click listener
        btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }

}
