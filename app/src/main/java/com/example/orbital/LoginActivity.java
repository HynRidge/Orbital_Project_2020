package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {
    EditText phoneNumber, password;
    Button loginBtn;
    TextView needAnAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phoneNumber = findViewById(R.id.phone_number_login);
        password = findViewById(R.id.password_login);
        loginBtn = findViewById(R.id.login_button);
        needAnAcc = findViewById(R.id.need_an_account);


        //Toast.makeText(getApplicationContext(),"Welcome to Close Friends",Toast.LENGTH_SHORT).show();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToMainActivity();
            }
        });

        needAnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToRegisterActivity();
            }
        });
    }

    private void sendUserToRegisterActivity() {
        Intent activity = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(activity);
    }

    private void sendUserToMainActivity() {
        Intent activity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(activity);
    }
}
