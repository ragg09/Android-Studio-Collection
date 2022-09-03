package com.example.listintentnewactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView pangalan, bago;
    String name, newname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pangalan = (TextView)findViewById(R.id.tvname);

        name = ((MyApplication) this.getApplication()).getName();

        pangalan.setText(name);

        ((MyApplication) this.getApplication()).setName("Angelo");

        newname = ((MyApplication) this.getApplication()).getName();

        bago.setText(newname);

    }
}