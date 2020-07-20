package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class AddContactPg extends AppCompatActivity {

    EditText phoneNumberEt;

    Button submitButton;

    String BASE_URL = " http://172.31.123.95:8000/account/";

    int CURRENT_USER_ID =LoginActivity.USER_ID;

    int NEW_CONTACT_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_pg);
        initialize();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!PhoneNumberUtils.isGlobalPhoneNumber(String.valueOf(phoneNumberEt)) ||
                phoneNumberEt.getText().toString().isEmpty()) {
                    phoneNumberEt.setError("Please enter a valid phone number");
                    phoneNumberEt.requestFocus();
                }
                findContact();
            }
        });
    }

    private void findContact() {
        String url = BASE_URL + "register-by-phone-number/" + phoneNumberEt.getText().toString() +"/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo;
                    JSONArray jsonArray = new JSONArray(response);
                    jo = jsonArray.getJSONObject(0);
                    if(jo.isNull("id")) {
                        Toast.makeText(AddContactPg.this, "This phone number is not found, please try again!", Toast.LENGTH_SHORT).show();
                    }
                    NEW_CONTACT_ID = jo.getInt("id");
                    updateContactDatabase();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddContactPg.this, "Server response has failed, try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateContactDatabase() {
        String url = BASE_URL + "get-contact/" + CURRENT_USER_ID;
    }

    private void processResponse(String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);
    }

    private void initialize() {
        phoneNumberEt = (EditText) findViewById(R.id.phoneNumberEt);
        submitButton = (Button) findViewById(R.id.submitButton);
    }
}