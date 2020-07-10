package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText enterPassword,enterConfirmPassword,firstName,lastName,email,phoneNumber;
    TextView passWord,confirmPassWord;
    Button registerBtn;
    static String passwordSaved,firstNameSaved,lastNameSaved,emailSaved,phoneNumberSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialiseFields();

        //Instantiate RequestQueue

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedCredentials();
                sendUserToRegister2Activity();
            }
        });

    }

    private void savedCredentials() {
        firstNameSaved = firstName.getText().toString().trim();
        lastNameSaved = lastName.getText().toString().trim();
        emailSaved = email.getText().toString().trim();
        passwordSaved = enterPassword.getText().toString().trim();
        phoneNumberSaved = phoneNumber.getText().toString().trim();
    }

    //    private void sendUserToLoginActivity() {
//        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//        finish();
//    }
    private void sendUserToRegister2Activity() {
        if(validate()) {
            Intent intent = new Intent(RegisterActivity.this, Register2Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
    private boolean validate() {
        ColorStateList colorStateList = ColorStateList.valueOf(getColor(R.color.colorError));


        if(TextUtils.isEmpty(firstNameSaved)) {
//            TextView firstNameRequired = findViewById(R.id.firstName);
//            firstNameRequired.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(),"First name required",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(lastNameSaved)) {
//            TextView lastNameRequired = findViewById(R.id.lastName);
//            lastNameRequired.setVisibility(View.VISIBLE);
//
//            lastName.setBackgroundTintList(colorStateList);

            Toast.makeText(getApplicationContext(),"Last name required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(emailSaved)) {
//            TextView emailRequired = findViewById(R.id.enterEmail);
//            emailRequired.setVisibility(View.VISIBLE);
//
//            lastName.setBackgroundTintList(colorStateList);
            Toast.makeText(getApplicationContext(),"Email address required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(passwordSaved)) {
//            TextView emailRequired = findViewById(R.id.enterEmail);
//            emailRequired.setVisibility(View.VISIBLE);
//
//            lastName.setBackgroundTintList(colorStateList);
            Toast.makeText(getApplicationContext(),"Password required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(passwordSaved)) {
//            TextView emailRequired = findViewById(R.id.enterEmail);
//            emailRequired.setVisibility(View.VISIBLE);
//
//            lastName.setBackgroundTintList(colorStateList);
            Toast.makeText(getApplicationContext(),"Password required",Toast.LENGTH_SHORT).show();
            return false;
        }
        String confirmPasswordSaved = enterConfirmPassword.getText().toString().trim();
        if(TextUtils.isEmpty(confirmPasswordSaved)) {
//            TextView emailRequired = findViewById(R.id.enterEmail);
//            emailRequired.setVisibility(View.VISIBLE);
//
//            lastName.setBackgroundTintList(colorStateList);
            Toast.makeText(getApplicationContext(),"Password required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(phoneNumberSaved)) {
//            TextView emailRequired = findViewById(R.id.enterEmail);
//            emailRequired.setVisibility(View.VISIBLE);
//
//            lastName.setBackgroundTintList(colorStateList);
            Toast.makeText(getApplicationContext(),"Phone number required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!confirmPasswordSaved.equals(passwordSaved)) {
            Toast.makeText(getApplicationContext(),"Please enter the correct password",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initialiseFields() {
        enterPassword = (EditText) findViewById(R.id.enterPassword);
        enterConfirmPassword = (EditText) findViewById(R.id.enterConfirmPassword);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.enterEmail);
        phoneNumber = (EditText) findViewById(R.id.enter_phone_number);
        registerBtn = (Button) findViewById(R.id.next_page_button);
    }
}


