package com.example.orbital;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orbital.Model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    Toolbar chatToolBar;

    RecyclerView recyclerView;

    TextView nickname;

    ImageView profileImage;

    EditText edit_message;

    Button sendButton;

    private MessageAdapter messageAdapter;

    Intent intent;

    int CURRENT_USER_ID = LoginActivity.USER_ID;
    int CONTACT_ID;
    String CONTACT_NICKNAME;
    int CONTACT_IMG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        initialize();

        intent = getIntent();
        CONTACT_ID = intent.getIntExtra("contactId",0);
        CONTACT_NICKNAME = intent.getStringExtra("contactNickname");
        CONTACT_IMG = intent.getIntExtra("contactImage", R.drawable.defaultpic);

        nickname.setText(CONTACT_NICKNAME);
        profileImage.setImageResource(CONTACT_IMG);

    }

    private void initialize() {

        chatToolBar = findViewById(R.id.toolbar);

        setSupportActionBar(chatToolBar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        chatToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowCustomEnabled(true);

//        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View actionBarView = layoutInflater.inflate(R.layout.toolbar,null);
//        actionBar.setCustomView(actionBarView);


//        recyclerView = findViewById(R.id.message_recycler_view);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        linearLayoutManager.setStackFromEnd(true);

        nickname = findViewById(R.id.username);

        profileImage = findViewById(R.id.custom_profile_image);

//        sendButton = findViewById(R.id.send_button);

//        edit_message = findViewById(R.id.edit_message);
    }
}