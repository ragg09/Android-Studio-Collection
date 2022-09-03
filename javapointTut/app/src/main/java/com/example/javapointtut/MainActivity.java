package com.example.javapointtut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnCalcu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalcu = (Button) findViewById(R.id.btnCalcu);

        //creating onclick listerner
        btnCalcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalcuActivity();
            }
        });
    }

    //opening new activity
    public void openCalcuActivity(){
        Intent intent = new Intent(this, Calculator.class);
        startActivity(intent);
    }
}