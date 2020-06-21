package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class RegisterActivity extends AppCompatActivity {
    EditText enterPassword,enterConfirmPassword,firstName,lastName,email,phoneNumber;
    TextView passWord,confirmPassWord,alreadyHaveAccountLink;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialiseFields();
        registerBtn.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               sendUserToRegister2Activity();
                                           }
                                       }
        );
        alreadyHaveAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToLoginActivity();
            }
        });
    }
    private void sendUserToLoginActivity() {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void sendUserToRegister2Activity() {
        if(validate()) {
            Intent intent = new Intent(RegisterActivity.this, Register2Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
    private boolean validate() {
        String userFirstName = firstName.getText().toString().trim();
        if(TextUtils.isEmpty(userFirstName)) {
            Toast.makeText(this,"Please enter your first name",Toast.LENGTH_SHORT).show();
        }
        String userLastName = lastName.getText().toString().trim();
        if(TextUtils.isEmpty(userLastName)) {
            Toast.makeText(this,"Please enter your last name",Toast.LENGTH_SHORT).show();
        }
    }
    private void initialiseFields() {
        enterPassword = (EditText) findViewById(R.id.enterPassword);
        enterConfirmPassword = (EditText) findViewById(R.id.enterConfirmPassword);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.enterEmail);
        phoneNumber = (EditText) findViewById(R.id.enter_phone_number);
        registerBtn = (Button) findViewById(R.id.next_page_button);
        alreadyHaveAccountLink = (TextView) findViewById(R.id.already_have_account_link);
    }
}
