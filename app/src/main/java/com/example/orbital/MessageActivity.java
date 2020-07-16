
package com.example.closefriendsapp;

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

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private Toolbar chatToolBar;

    private RecyclerView view;

    private TextView name, typeMessageHint;

    private ImageView profileImage;

    private Button sendButton;

    private MessageAdapter messageAdapter;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orbital.Model.Message;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

     private Toolbar chatToolBar;

     private RecyclerView recyclerView;

     CircleImageView profile_image;

     private TextView nickname;

     private EditText text_send;

     private ImageView profileImage;

     private Button sendButton;

     private MessageAdapter messageAdapter;


    private final List<Message> messagesList = new ArrayList<>();

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


        view = findViewById(R.id.rv_chats);

        name = findViewById(R.id.username);

        typeMessageHint = findViewById(R.id.edit_message);

        profileImage = findViewById(R.id.custom_profile_image);

        sendButton = findViewById(R.id.rounded_send_button);

        recyclerView = findViewById(R.id.message_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);

        nickname = findViewById(R.id.username);

        text_send = findViewById(R.id.edit_message);

        profileImage = findViewById(R.id.custom_profile_image);

        sendButton = findViewById(R.id.send_button);
    }
}