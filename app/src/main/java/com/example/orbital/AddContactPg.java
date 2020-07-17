package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class AddContactPg extends AppCompatActivity {

    EditText phoneNumberEt;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_pg);
        initialize();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!PhoneNumberUtils.isGlobalPhoneNumber(String.valueOf(phoneNumberEt))) {
                    phoneNumberEt.setError("Please enter a valid phone number");
                    phoneNumberEt.requestFocus();
                }
            }
        });
    }
    private void initialize() {
        phoneNumberEt = (EditText) findViewById(R.id.phoneNumberEt);
        submitButton = (Button) findViewById(R.id.submitButton);
    }
}