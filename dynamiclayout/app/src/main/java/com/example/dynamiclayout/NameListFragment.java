package com.example.dynamiclayout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NameListFragment extends Fragment {

    private OnNameSelectedListener callback;

    public interface OnNameSelectedListener {
        void onNameSelected(Candidate candidate);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNameSelectedListener) {
            callback = (OnNameSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() +
                    " must implement OnNameSelectedListener.");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name_list, container, false);
        ListView listView = view.findViewById(R.id.name_list_view);

        final Candidate[] candidates = {
                new Candidate("Alice", "Details about Alice."),
                new Candidate("Bob", "Details about Bob."),
                new Candidate("Charlie", "Details about Charlie.")
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1,
                new String[]{"Alice", "Bob", "Charlie"});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            if (callback != null) {
                callback.onNameSelected(candidates[position]);
            }
            Candidate candidate = candidates[position];
            DetailsFragment detailsFragment = DetailsFragment.newInstance(candidate);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailsFragment)
                    .addToBackStack(null) // Add to back stack for Back navigation
                    .commit();
        });

        return view;
    }




}

