package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Register2Activity extends AppCompatActivity {
    TextView birthday, nickname;
    EditText enterBirthday, enterNickname;
    Button submitBtn;
    RequestQueue queue;
    String url = "http://172.31.123.95:8000/account/register/";
    String firstNameSaved = RegisterActivity.firstNameSaved;
    String lastNameSaved = RegisterActivity.lastNameSaved;
    String emailSaved = RegisterActivity.emailSaved;
    String passwordSaved = RegisterActivity.passwordSaved;
    String phoneNumberSaved = RegisterActivity.phoneNumberSaved;
    static long USER_ID ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        birthday = findViewById(R.id.birthdayTv);
        nickname = findViewById(R.id.nicknameTv);
        submitBtn = findViewById(R.id.button_reg_2);
        enterBirthday = findViewById(R.id.birthdayEt);
        enterNickname = findViewById(R.id.nicknameEt);

        queue = Volley.newRequestQueue(getApplicationContext());

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()) {
                    System.out.println(firstNameSaved + " " + lastNameSaved + " " + emailSaved + " " + passwordSaved + " " + phoneNumberSaved + ' ' + enterNickname.getText().toString().trim()
                            + " " + enterBirthday.getText().toString().trim());
                    register();
                }
            }
        });
}

    private void sendUserToLoginActivity() {
        Intent activity = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(activity);
    }

    private void register() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
                processRegisterResponse(response);
                sendUserToLoginActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                NetworkResponse response = error.networkResponse;
//                if (error instanceof ServerError && response != null) {
//                    try {
//                        String res = new String(response.data,
//                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
//                        // Now you can use any deserializer to make sense of data
//                        JSONObject obj = new JSONObject(res);
//                    } catch (UnsupportedEncodingException | JSONException e1) {
//                        // Couldn't properly decode data to string
//                        e1.printStackTrace();
//                    } // returned data is not JSONObject?
//
//                }
                Log.d("Failed", error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("first_name",firstNameSaved);
                params.put("last_name",lastNameSaved);
                params.put("email",emailSaved);
                params.put("password",passwordSaved);
                params.put("phone_number", phoneNumberSaved);
                params.put("nickname",enterNickname.getText().toString().trim());

                //Format input date
                SimpleDateFormat input = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat output  = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date inputBirthday = input.parse(enterBirthday.getText().toString().trim());
                    assert inputBirthday != null;
                    params.put("birthday",output.format(inputBirthday));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return params;
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }
        };
        queue.add(stringRequest);
    }

    private void processRegisterResponse(String response) {
        ArrayList<String> stringResponse = new ArrayList();
        String[] responses = response.split(",");
//        System.out.println(responses.length);
        for (String response1 : responses) {
            String[] responses2 = response1.split(":");
//            System.out.println(responses2[1]);
            stringResponse.add(responses2[1]);
        }
        USER_ID = Long.parseLong(stringResponse.get(0).toString());
        System.out.println(USER_ID);
    }

    private boolean isValidate() {
        String nicknameSaved = nickname.getText().toString().trim();
        if (TextUtils.isEmpty(nicknameSaved)) {
//            TextView emailRequired = findViewById(R.id.enterEmail);
//            emailRequired.setVisibility(View.VISIBLE);
//
//            lastName.setBackgroundTintList(colorStateList);
            Toast.makeText(getApplicationContext(), "Nickname required", Toast.LENGTH_SHORT).show();
            return false;
        }
        String birthdaySaved = birthday.getText().toString().trim();
        if (TextUtils.isEmpty(birthdaySaved)) {
//            TextView emailRequired = findViewById(R.id.enterEmail);
//            emailRequired.setVisibility(View.VISIBLE);
//
//            lastName.setBackgroundTintList(colorStateList);
            Toast.makeText(getApplicationContext(), "Birthday required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
