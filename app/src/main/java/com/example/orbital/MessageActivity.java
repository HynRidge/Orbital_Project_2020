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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orbital.Model.Message;
import com.example.orbital.Model.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageActivity extends AppCompatActivity {

    Toolbar chatToolBar;

    RecyclerView recyclerView;

    TextView nickname;

    ImageView profileImage;

    EditText edit_message;

    ImageButton sendButton;


    private MessageAdapter messageAdapter = new MessageAdapter();

    Intent intent;

    int CURRENT_USER_ID = LoginActivity.USER_ID;

    int CONTACT_ID;
    String CONTACT_NICKNAME;
    int CONTACT_IMG;
    int ROOM_ID;


    ArrayList<Message> msg;

    String BASE_URL = URL.BASE_URL;
    RequestQueue queue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        queue = Volley.newRequestQueue(getApplicationContext());
        initialize();

        showMessages();


        messageAdapter.setMessage(MessageActivity.this,msg);
        recyclerView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBackend();
            }
        });




    }

    private void updateBackend() {
        String url = BASE_URL +"add-message/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               showMessages();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MessageActivity.this, "Failed to send message", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("room",Integer.toString(ROOM_ID));
                params.put("sender_user",Integer.toString(CURRENT_USER_ID));
                params.put("receiver_user",Integer.toString(CONTACT_ID));
                params.put("message",edit_message.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void showMessages() {
        String url = BASE_URL + "get-message/" + ROOM_ID +"/";
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                msg.clear();
                try {
                    JSONArray jsonArray =  new JSONArray(response);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        msg.add(new Message(jsonObject.getInt("sender_user"),ROOM_ID,jsonObject.getString("message")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MessageActivity.this, "Failed to show messages", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);

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


        recyclerView = findViewById(R.id.message_rec_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        nickname = findViewById(R.id.username);
        profileImage = findViewById(R.id.custom_profile_image);
        sendButton = findViewById(R.id.send_button);
        edit_message = findViewById(R.id.text_send);


        intent = getIntent();
        CONTACT_ID = intent.getIntExtra("contactId", -1);
        CONTACT_NICKNAME = intent.getStringExtra("contactNickname");
        CONTACT_IMG = intent.getIntExtra("contactImage", R.drawable.defaultpic);
        ROOM_ID = intent.getIntExtra("roomID", -1);
        msg = intent.getParcelableArrayListExtra("messages");

        nickname.setText(CONTACT_NICKNAME);
        profileImage.setImageResource(CONTACT_IMG);

        msg = new ArrayList<Message>();
    }
}