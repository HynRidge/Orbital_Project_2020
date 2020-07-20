package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Friends extends AppCompatActivity {

    Button backButton,saveChangesButton;


    EditText nicknameEt, statusEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_friends);

        backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);

                finish();
            }
        });
        saveChangesButton = (Button) findViewById(R.id.saveChangesButton);

        saveChangesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty((CharSequence) nicknameEt)) {

                    Toast.makeText(getApplicationContext(),"Please Enter A Nickname first",Toast.LENGTH_SHORT).show();

                    return;
                }
                if(TextUtils.isEmpty((CharSequence) statusEt)) {

                    Toast.makeText(getApplicationContext(),"Please Enter A Status First",Toast.LENGTH_SHORT).show();

                    return;
                }
                Toast.makeText(getApplicationContext(),"Changes Updated Successfully",Toast.LENGTH_SHORT).show();

                return;
            }
        });

        nicknameEt = (EditText) findViewById(R.id.changeNicknameEt);

        statusEt = (EditText) findViewById(R.id.UpdateStatusEt);
    }
}