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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddContactPg extends AppCompatActivity {

    EditText phoneNumberEt;

    Button submitButton;

    String BASE_URL = " http://172.31.123.95:8000/account/";

    int CURRENT_USER_ID =LoginActivity.USER_ID;

    RequestQueue queue;

    int NEW_CONTACT_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_pg);
        initialize();

        queue = Volley.newRequestQueue(getApplicationContext());
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumberEt.getText().toString().isEmpty()) {
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

        queue.add(stringRequest);
    }

    private void updateContactDatabase() {
        String url = BASE_URL + "contact/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddContactPg.this, "Contact has been added", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddContactPg.this, "Response failure,please try again", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("current_user_id",Integer.toString(CURRENT_USER_ID));
                params.put("contact",Integer.toString(NEW_CONTACT_ID));
                return params;
            }
        };

        queue.add(stringRequest);
    }

    private void initialize() {
        phoneNumberEt = (EditText) findViewById(R.id.phoneNumberEt);
        submitButton = (Button) findViewById(R.id.submitButton);
    }
}