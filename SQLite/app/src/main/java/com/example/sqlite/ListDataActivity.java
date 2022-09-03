package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView lv;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        lv = (ListView)findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();


    }

    public void populateListView(){
        Log.d(TAG,"DISPLAYING DATA IN THE LISTVIEW");

        //get data and append to list
        Cursor data = mDatabaseHelper.getStudentData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //itong part na to is ung clickable ng list and mappunta sa panibagong avtivity para madisplay ung data nung pinindot
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Cursor data = mDatabaseHelper.getStudentID(name); //get the id associated with that name
                while(data.moveToNext()){
                    String stud_id= data.getString(0);
                    String stud_name= data.getString(1);
                    String stud_lname= data.getString(2);
                    String stud_course= data.getString(3);

                    //Toast.makeText(getApplicationContext(),stud_id,Toast.LENGTH_SHORT).show();
                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditStudentActivity.class);
                    editScreenIntent.putExtra("id",stud_id);
                    editScreenIntent.putExtra("name",stud_name);
                    editScreenIntent.putExtra("lname",stud_lname);
                    editScreenIntent.putExtra("course",stud_course);
                    startActivity(editScreenIntent);
                    finish();
                }




            }
        });
    }

}
