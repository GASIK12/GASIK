package com.example.myapplication.Activity;

import android.os.Bundle;


import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

public class MenuActivity extends PublicToolbar {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
    }
}