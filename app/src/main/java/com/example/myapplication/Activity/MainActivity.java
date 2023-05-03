package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button menu_move;
    private Button restaurant_move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu_move = findViewById(R.id.menuButton);
        restaurant_move = findViewById(R.id.restaurantButton);

        // menuActivity로 이동
        menu_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent); // Activity 이동
            }
        });

        // RestaurantActivity로 이동
        restaurant_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                startActivity(intent); // Activity 이동
            }
        });

        //툴바
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
}