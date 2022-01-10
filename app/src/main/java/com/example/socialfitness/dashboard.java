package com.example.socialfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class dashboard extends AppCompatActivity {

    BottomNavigationView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        view = findViewById(R.id.bottomnav);


        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){

                        case R.id.home:
                            Toast.makeText(dashboard.this, "Home Selected", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.progress:
                            Toast.makeText(dashboard.this, "Progress", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.ranks:
                            Toast.makeText(dashboard.this, "Ranks", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.profile:
                            Toast.makeText(dashboard.this, "Profile", Toast.LENGTH_SHORT).show();
                            break;

                    }


                return true;
            }
        });


    }
}