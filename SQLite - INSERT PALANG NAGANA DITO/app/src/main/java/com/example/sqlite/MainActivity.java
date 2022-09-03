package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    EditText etname;
    Button btnadd, btnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname = (EditText)findViewById(R.id.etname);
        btnadd = (Button)findViewById(R.id.btnadd);
        btnview = (Button)findViewById(R.id.btnview);
        mDatabaseHelper = new DatabaseHelper(this);

        //add button trigger
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = etname.getText().toString();
                if(etname.length() != 0){
                    AddStudentData(newEntry);
                    etname.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"PLEASE PUT SOME TEXT", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //view button to see list of added data
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });


    }

    //add function call to be inserted to DatabaseHelper class
    public void AddStudentData(String newEntry){
        boolean insertData = mDatabaseHelper.addStudentData(newEntry);

        if(insertData){
            Toast.makeText(getApplicationContext(),"DATA SUCCESSFULLY INSERTED", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"SOMETHING WENT WRONG", Toast.LENGTH_SHORT).show();
        }
    }


}