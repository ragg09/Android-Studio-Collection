package com.example.android_api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailView extends AppCompatActivity {
    private TextView nametxt;
    private ImageView fullimg;
    //private static final String urlString ="http://192.168.1.90/storage/images/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        nametxt=(TextView)findViewById(R.id.name_txt);
        fullimg=(ImageView)findViewById(R.id.full_image);
        Intent i=getIntent();

        String name=i.getStringExtra("name");
        String imageurl=i.getStringExtra("imageurl");


        nametxt.setText(name);

        Picasso.get()
                .load(imageurl)
                .into(fullimg);
        Log.i("url","test"+imageurl);

    }

}

