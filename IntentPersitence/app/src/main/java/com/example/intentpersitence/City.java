package com.example.intentpersitence;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class City extends AppCompatActivity {
    ListView listview;
    //creating array adapter
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        //setting up the list view
        listview = (ListView)findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.city));
        listview.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        //making each list clickable
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String city_name = arrayAdapter.getItem(position);
                Toast.makeText(getApplicationContext(),city_name,Toast.LENGTH_SHORT).show();
            }
        });


    }
}