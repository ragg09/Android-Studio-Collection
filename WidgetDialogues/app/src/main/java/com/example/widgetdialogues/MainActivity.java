package com.example.widgetdialogues;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn1;
    ToggleButton tg1;

    Button cbbtn;
    CheckBox cb1, cb2, cb3;
    TextView cbtext;

    Button btnAlert;

    AlertDialog.Builder builder;

    Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        tg1 = (ToggleButton)findViewById(R.id.tg1) ;
        Toast msg = Toast.makeText(getApplicationContext(),"THIS IS THE TOAST MESSAGE", Toast.LENGTH_SHORT);

        cbbtn = (Button)findViewById(R.id.cbbtn);
        cb1 = (CheckBox)findViewById(R.id.cb1);
        cb2 = (CheckBox)findViewById(R.id.cb2);
        cb3 = (CheckBox)findViewById(R.id.cb3);
        cbtext = (TextView)findViewById(R.id.cbtext);

        btnAlert = (Button)findViewById(R.id.btnAlert);
        builder = new AlertDialog.Builder(this);

        //setting dropdown menu | spinner, the array came from text resource
        spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.show();

            }
        });

        tg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tg1.isChecked()){
                    Toast.makeText(getApplicationContext(),"NAKA ON", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"NAKA OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb1.isChecked() || cb2.isChecked() || cb3.isChecked()){
                    StringBuilder ent = new StringBuilder();
                    ent.append("You Choose: ");
                    if(cb1.isChecked()){
                        ent.append("Song ");
                    }
                    if(cb2.isChecked()){
                        ent.append("Movie ");
                    }
                    if(cb3.isChecked()){
                        ent.append("and Book ");
                    }
                    cbtext.setText(""+ent.toString());
                }else {
                    cbtext.setText("You didnt check any option!");
                }
            }
        });


        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ung builder sinet ko sa taas part ito ng AlertDialog
                builder.setMessage("Ito ung pinaka message ng alert dialog")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"Pinindot mo ung OK", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"Pinindot mo ung NO", Toast.LENGTH_SHORT).show();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Ito ung magiging TiTLE ng ALERT");
                alert.show();

            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}