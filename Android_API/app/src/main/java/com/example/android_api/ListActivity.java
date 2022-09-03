package com.example.android_api;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URL;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private static final String urlString = "http://192.168.68.103:8000/api/item/";

    private List<item_data> list_data;
    private RecyclerView rv;
    private myAdapter adapter;
    private URL myRUL;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }
}
