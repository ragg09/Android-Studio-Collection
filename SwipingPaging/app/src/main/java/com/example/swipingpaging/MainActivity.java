package com.example.swipingpaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private DemoFragmentCollectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager = (ViewPager)findViewById(R.id.pager);
        adapter = new DemoFragmentCollectionAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
    }
}