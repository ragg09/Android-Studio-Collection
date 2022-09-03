package com.example.classobject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //calling the Student class and creating student1 object
    private Student Student1;
    EditText name, year, section, course;
    TextView vname, vyear, vsection, vcourse;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.ptname);
        vname = (TextView)findViewById(R.id.tvname);

        section = (EditText)findViewById(R.id.ptsection);
        vsection = (TextView)findViewById(R.id.tvsection);

        year = (EditText)findViewById(R.id.ptyear);
        vyear = (TextView)findViewById(R.id.tvyear);

        course = (EditText)findViewById(R.id.ptcourse);
        vcourse = (TextView)findViewById(R.id.tvcourse);

        btn1 = (Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                Integer y = Integer.valueOf(year.getText().toString());
                String s = section.getText().toString();
                String c = course.getText().toString();

                //using the object student
                Student1 = new Student(n,y,s,c);

                vname.setText(""+Student1.getName());
                vyear.setText(""+Student1.getYear());
                vsection.setText(""+Student1.getSection());
                vcourse.setText(""+Student1.getCourse());

                name.setText("");
                year.setText("");
                section.setText("");
                course.setText("");
            }
        });




    }
}