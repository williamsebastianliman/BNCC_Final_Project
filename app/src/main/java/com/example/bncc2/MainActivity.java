package com.example.bncc2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //Make the counter fragment open first after login
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new CounterFragment()).commit();
            bottomNav.setSelectedItemId(R.id.nav_counter); // Set counter as default
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_counter:
                        selectedFragment = new CounterFragment();
                        break;
                    case R.id.nav_area_calculator:
                         selectedFragment = new AreaCalculatorFragment();
                        break;
                    case R.id.nav_volume_calculator:
                         selectedFragment = new VolumeCalculatorFragment();
                        break;
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                }

                return true;
            };
}