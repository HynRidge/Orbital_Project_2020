package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {
    EditText phoneNumber, password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phoneNumber = findViewById(R.id.phone_number_login);
        password = findViewById(R.id.password_login);
        loginBtn = findViewById(R.id.login_button);


        //Toast.makeText(getApplicationContext(),"Welcome to Close Friends",Toast.LENGTH_SHORT).show();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToMainActivity();
            }
        });
    }

    private void sendUserToMainActivity() {
       Intent activity = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(activity);
    }
}
