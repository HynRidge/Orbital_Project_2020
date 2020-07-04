package com.example.orbital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    ViewPager myViewPager;
    TabLayout myTabLayout;
    TabsAccessorAdapter tabsAccessorAdapter;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context = getApplicationContext();

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

    public static Context getAppContext() {
        return MainActivity.context;
    }
}
