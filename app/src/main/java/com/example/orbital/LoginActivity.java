package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText phoneNumber, password;
    Button loginBtn;
    TextView needAnAcc;
    RequestQueue queue;
    String url ="http://172.31.123.95:8000/account/api/token/login/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_2);
        phoneNumber = findViewById(R.id.phone_number_login);
        password = findViewById(R.id.password_login);
        loginBtn = findViewById(R.id.login_button);
        needAnAcc = findViewById(R.id.need_an_account);

        queue = Volley.newRequestQueue(getApplicationContext());

        //Toast.makeText(getApplicationContext(),"Welcome to Close Friends",Toast.LENGTH_SHORT).show();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });

        needAnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToRegisterActivity();
            }
        });
    }

    private void LoginUser() {
        if(IsValidated()) {
            login();
        }
    }

    private void login() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                Log.d("Server responded",response);
                sendUserToMainActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response failed", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =  new HashMap<>();
                params.put("phone_number", phoneNumber.getText().toString().trim());
                params.put("password",password.getText().toString().trim());
                return params;
            }
        };
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                6000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
//        );
        queue.add(stringRequest);
    }



    private boolean IsValidated() {
        if(phoneNumber.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Fill in the required Field",Toast.LENGTH_SHORT);
            return false;
        }
        return true;
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
