package com.example.orbital;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
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

    private Toolbar chatToolBar;

    private RecyclerView recyclerView;

    private TextView nickname;

    private ImageView profileImage;

    EditText edit_message;

    private Button sendButton;

    private MessageAdapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        initialize();

    }

    @SuppressLint("RestrictedApi")
    private void initialize() {

        chatToolBar = findViewById(R.id.chat_toolbar);

        setSupportActionBar(chatToolBar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View actionBarView = layoutInflater.inflate(R.layout.toolbar,null);
        actionBar.setCustomView(actionBarView);


        recyclerView = findViewById(R.id.message_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);

        nickname = findViewById(R.id.username);

        profileImage = findViewById(R.id.custom_profile_image);

        sendButton = findViewById(R.id.send_button);
    }
}