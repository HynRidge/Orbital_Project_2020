package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Register2Activity extends AppCompatActivity {
    TextView birthday,nickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        birthday = findViewById(R.id.birthdayTv);
        nickname = findViewById(R.id.nicknameTv);
    }
}