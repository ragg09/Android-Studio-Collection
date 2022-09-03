package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditStudentActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnupdate,btndelete;
    private TextView student_id;
    private EditText etname_edit,etlname_edit,etcourse_edit;

    DatabaseHelper mDatabaseHelper;

    private String selectedname;
    private String selectedlname;
    private String selectedcourse;
    private Integer selectedid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_student_layout);

        btnupdate = (Button) findViewById(R.id.btnupdate);
        btndelete = (Button) findViewById(R.id.btndelete);

        student_id = (TextView)findViewById(R.id.student_id);
        etname_edit = (EditText) findViewById(R.id.etname_edit);
        etlname_edit = (EditText) findViewById(R.id.etlname_edit);
        etcourse_edit = (EditText) findViewById(R.id.etcourse_edit);

        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //getting data from listview onclick
        //now get the data we passed as an extra
        selectedid = Integer.valueOf(receivedIntent.getStringExtra("id"));
        selectedname = receivedIntent.getStringExtra("name");
        selectedlname = receivedIntent.getStringExtra("lname");
        selectedcourse = receivedIntent.getStringExtra("course");

        //putting value to Edit text
        student_id.setText("You ID: "+selectedid);
        etname_edit.setText(selectedname);
        etlname_edit.setText(selectedlname);
        etcourse_edit.setText(selectedcourse);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = selectedid;
                String name = etname_edit.getText().toString();
                String lname = etlname_edit.getText().toString();
                String course = etcourse_edit.getText().toString();

                if(name.equals("") || lname.equals("") || course.equals("")){
                    Toast.makeText(getApplicationContext(), "DAPAT MAY LAMAN LAHAT", Toast.LENGTH_SHORT).show();
                }else{
                    mDatabaseHelper.updateStudentData(id, name, lname, course);
                    Toast.makeText(getApplicationContext(), "UPDATED NA!!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = selectedid;
                mDatabaseHelper.deleteStudentData(id);
                Toast.makeText(getApplicationContext(), "DATA DELETED", Toast.LENGTH_SHORT).show();
                finish();
            }
        });






    }

}
