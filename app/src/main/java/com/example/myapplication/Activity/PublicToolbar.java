package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.myapplication.R;

public class PublicToolbar extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                return true;
            case R.id.action_map:
                Intent intent_map = new Intent(this, MapActivity.class);
                startActivity(intent_map);
                return true;

            case R.id.action_user:
                Intent intent_user = new Intent(this, UserActivity.class);
                startActivity(intent_user);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}