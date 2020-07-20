package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    ViewPager myViewPager;
    TabLayout myTabLayout;
    TabsAccessorAdapter tabsAccessorAdapter;
    private static Context context;

    FloatingActionButton add_contat_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        add_contat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToAddContactPage();
            }
        });


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

        add_contat_btn = findViewById(R.id.add_contact_button);

        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Close Friends");

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
