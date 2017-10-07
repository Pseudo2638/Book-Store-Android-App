package com.example.hp.bookshop;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(viewPageAdapter);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_view);
        tabLayout.setupWithViewPager(viewPager);
    }
}
