package com.example.javapointtut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {
    private Button btnMain, btnAdd, btnSubtract, btnMultiply, btnDivide;
    private EditText num1, num2;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        btnMain = (Button) findViewById(R.id.btnMain);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSubtract = (Button) findViewById(R.id.btnSubtract);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);

        num1 = (EditText) findViewById(R.id.num1) ;
        num2 = (EditText) findViewById(R.id.num2) ;

        result = (TextView) findViewById(R.id.result);


        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMain();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subtract();
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Multiply();
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Divide();
            }
        });
    }

    //METHOOOOOOOOOOOOODS

    public void goMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //this.finish();
    }

    public void Add(){
        int a = Integer.parseInt(num1.getText().toString());
        int b = Integer.parseInt(num2.getText().toString());
        int sum = a + b;
        result.setText(""+sum);
        //Toast.makeText(getApplicationContext(),String.valueOf(sum), Toast.LENGTH_LONG).show();
    }

    public void Subtract(){
        int a = Integer.parseInt(num1.getText().toString());
        int b = Integer.parseInt(num2.getText().toString());
        int sum = a - b;
        result.setText(""+sum);
        //Toast.makeText(getApplicationContext(),String.valueOf(sum), Toast.LENGTH_LONG).show();
    }

    public void Multiply(){
        int a = Integer.parseInt(num1.getText().toString());
        int b = Integer.parseInt(num2.getText().toString());
        int sum = a * b;
        result.setText(""+sum);
        //Toast.makeText(getApplicationContext(),String.valueOf(sum), Toast.LENGTH_LONG).show();
    }

    public void Divide(){
        int a = Integer.parseInt(num1.getText().toString());
        int b = Integer.parseInt(num2.getText().toString());
        int sum = a / b;
        result.setText(""+sum);
        //Toast.makeText(getApplicationContext(),String.valueOf(sum), Toast.LENGTH_LONG).show();
    }

}