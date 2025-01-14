package com.example.dynamiclayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements NameListFragment.OnNameSelectedListener {

    private boolean isDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the screen is dual-pane (landscape mode)
        isDualPane = findViewById(R.id.details_fragment_container) != null;

        if (savedInstanceState == null) { // Prevent adding fragments again after rotation
            NameListFragment nameListFragment = new NameListFragment();

            if (isDualPane) {
                // Landscape mode: Add NameListFragment to the left container
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.list_fragment_container, nameListFragment)
                        .commit();
            } else {
                // Portrait mode: Add NameListFragment to the full container
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nameListFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onNameSelected(Candidate candidate) {
        if (isDualPane) {
            DetailsFragment detailsFragment = DetailsFragment.newInstance(candidate);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_fragment_container, detailsFragment)
                    .commit();
        } else {
            DetailsFragment detailsFragment = DetailsFragment.newInstance(candidate);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailsFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
