package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    ViewPager myViewPager;
    TabLayout myTabLayout;
    TabsAccessorAdapter tabsAccessorAdapter;
    private static Context context;

    FloatingActionButton add_contact_btn;
    FloatingActionButton change_nickname_btn;

    int CURRENT_USER_ID= LoginActivity.USER_ID;
    String CURRENT_USER_NICKNAME;
    String BASE_URL = getString(R.string.base_url);

    RequestQueue queue ;


    @Override
    protected void onPostResume() {
        super.onPostResume();
        getCurrentUserNickname();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(getApplicationContext());

        initialize();

        getCurrentUserNickname();


        add_contact_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToAddContactPage();
            }
        });

        change_nickname_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToChangeNicknamePage();
            }
        });


    }

    private void getCurrentUserNickname() {
        String url = BASE_URL + "get-nickname/" + CURRENT_USER_ID+"/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mToolbar = findViewById(R.id.main_page_toolbar);
                setSupportActionBar(mToolbar);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("nickname").equals("")) {
                        getSupportActionBar().setTitle("Anonymous User");
                    } else {
                        getSupportActionBar().setTitle(jsonObject.getString("nickname"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

    private void sendUserToChangeNicknamePage() {
        Intent activity = new Intent(getApplicationContext(), Friends.class);
        startActivity(activity);
    }

    private void sendUserToAddContactPage() {
        Intent activity = new Intent(getApplicationContext(),AddContactPg.class);
        startActivity(activity);
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    public void initialize() {
        MainActivity.context = getApplicationContext();

        add_contact_btn = findViewById(R.id.add_contact_button);
        change_nickname_btn = findViewById(R.id.change_nickname_btn);

        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);


        myViewPager = findViewById(R.id.main_tabs_pager);
        tabsAccessorAdapter = new TabsAccessorAdapter(getSupportFragmentManager());

        tabsAccessorAdapter.addFragment(new ChatsFragment(), "Chats");
        tabsAccessorAdapter.addFragment(new GroupsFragment(),"Groups");
        tabsAccessorAdapter.addFragment(new ContactsFragment(), "Contacts");

        myViewPager.setAdapter(tabsAccessorAdapter);

        myTabLayout = findViewById(R.id.main_tabs);
        myTabLayout.setupWithViewPager(myViewPager);
    }
}
