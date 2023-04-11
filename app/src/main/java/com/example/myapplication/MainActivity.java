package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}