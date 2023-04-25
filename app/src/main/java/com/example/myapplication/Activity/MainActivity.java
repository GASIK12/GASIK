package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.User;
import com.example.myapplication.UserDao;
import com.example.myapplication.UserDatabase;

public class MainActivity extends AppCompatActivity {

    private UserDao mUserDao;
    private Toolbar toolbar;
    private Button btn_move;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_move = findViewById(R.id.menuButton);
        // menuActivity로 이동
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent); // Activity 이동
            }
        });

        UserDatabase database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class,  "SIKGA")
                .fallbackToDestructiveMigration()       // 스키마 버전 변경 가능
                .allowMainThreadQueries()               // Main Thread에서 DB에 IO(Input / Output)를 가능하게 함
                .build();

        mUserDao = database.userDao(); // 인터페이스 객체 할당

        // 데이터 삽입
        User user = new User(); // 객체 인스턴스 생성
        user.setName("김민재");
        user.setAge("26");
        user.setPhoneNumber("01012345678");

        mUserDao.setInsertUser(user);

        //툴바
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}