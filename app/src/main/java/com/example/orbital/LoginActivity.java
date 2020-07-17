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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText phoneNumber, password;
    Button loginBtn;
    TextView needAnAcc;
    RequestQueue queue;
    String USER_PHONE_NUMBER;
    String LOGIN_URL ="http://172.31.120.153:8000/account/api/token/login/";
    String BASE_URL = "http://172.31.120.153:8000/account/";
    public static int USER_ID;
    String accessToken,refreshToken;


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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Server responded",response);

//                System.out.println(USER_PHONE_NUMBER);

                processingResponse(response);

                //Make a request to get userID
                getUserCredentials();
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
                params.put("phone_number",phoneNumber.getText().toString().trim());
                params.put("password",password.getText().toString().trim());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void getUserCredentials() {
        String url = BASE_URL +"register-by-phone-number/"+phoneNumber.getText().toString().trim() +"/";
        final ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo;
                    JSONArray jsonArray = new JSONArray(response);
                    jo = jsonArray.getJSONObject(0);
                    USER_ID = jo.getInt("id");
                    System.out.println(USER_ID);
                    sendUserToMainActivity();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "There no account with this credentials", Toast.LENGTH_SHORT).show();
            }
        }){

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("key", "Value");
                return headers;
            }
        };
        queue.add(stringRequest);
    }

    private void processingResponse(String response) {
        ArrayList<String> stringResponse = new ArrayList();
        String[] responses = response.split(",");
//        System.out.println(responses.length);
        for (String response1 : responses) {
            String[] responses2 = response1.split(":");
//            System.out.println(responses2[1]);
            stringResponse.add(responses2[1]);
        }
        refreshToken =stringResponse.get(0).toString();
        accessToken = stringResponse.get(1).toString();
//        System.out.println(refreshToken);
//        System.out.println(accessToken);
    }

    private boolean IsValidated() {
        if(phoneNumber.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Fill in the required Field",Toast.LENGTH_SHORT).show();
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
