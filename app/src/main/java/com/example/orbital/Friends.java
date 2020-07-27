package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Friends extends AppCompatActivity {

    Button saveChangesButton;
    EditText nicknameEt;

    int CURRENT_USER_ID= LoginActivity.USER_ID;
    String BASE_URL = " http://172.31.123.95:8000/account/";

    RequestQueue queue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_friends);

        initialize();

        queue =  Volley.newRequestQueue(getApplicationContext());

        saveChangesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(nicknameEt.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(),"Please Enter A Nickname first",Toast.LENGTH_SHORT).show();

                    return;
                }
                updateBackend();
            }
        });

    }

    private void updateBackend() {
        String url = BASE_URL + "change-nickname/" + CURRENT_USER_ID +"/";
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Friends.this, "Nickname successfully change", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Friends.this, "Nickname change unsuccesfull.Please try again !!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("nickname",nicknameEt.getText().toString().trim());
                return params;
            }
        };

        queue.add(stringRequest);
    }

    private void initialize() {
        saveChangesButton = (Button) findViewById(R.id.saveChangesButton);
        nicknameEt = findViewById(R.id.changeNicknameEt);

    }
}