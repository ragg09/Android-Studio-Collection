package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    private Button btnAdd, btnMinus, btnReset, btnGrow, btnShrink, btnToggle;
    private int counter;
    public float size;

    private View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnAdd:
                    Adding();
                    break;
                case R.id.btnMinus:
                    Subtracting();
                    break;
                case R.id.btnReset:
                    Reset();
                    break;
                case R.id.btnGrow:
                    Grow();
                    break;
                case R.id.btnShrink:
                    Shrink();
                    break;
                case R.id.btnToggle:
                    Toggle();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assigning objects, setting up
        txt = (TextView) findViewById(R.id.mytext);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(clickListener);

        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(clickListener);

        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(clickListener);

        btnGrow = (Button) findViewById(R.id.btnGrow) ;
        btnGrow.setOnClickListener(clickListener);

        btnShrink = (Button) findViewById(R.id.btnShrink) ;
        btnShrink.setOnClickListener(clickListener);

        btnToggle = (Button) findViewById(R.id.btnToggle) ;
        btnToggle.setOnClickListener(clickListener);

        //default return view/display
        Reset();
    }

    private void Reset(){
        counter = 0;
        txt.setText(""+counter);
        //toast is like a pop up message
        Toast.makeText(MainActivity.this, "Counter set to 0", Toast.LENGTH_SHORT).show();
    }

    private void Adding(){
        counter++;
        txt.setText(""+counter);
    }

    private void Subtracting(){
        if(counter == 0){
            counter = 0;
            txt.setText(""+counter);
            //toast is like a pop up message
            Toast.makeText(MainActivity.this, "Value is already 0", Toast.LENGTH_SHORT).show();
        }else{
            counter--;
            txt.setText(""+counter);
        }
    }

    private void Grow(){
        size = txt.getTextScaleX();
        txt.setTextScaleX(size+1);
    }

    private void Shrink(){
        if(txt.getTextScaleX() == 0){
            Toast.makeText(MainActivity.this, "Size is already 0", Toast.LENGTH_SHORT).show();
        }else{
            size = txt.getTextScaleX();
            txt.setTextScaleX(size-1);
        }
    }

    private void Toggle(){
        if(txt.getVisibility() == View.VISIBLE){
            txt.setVisibility(View.INVISIBLE);
            btnToggle.setText("SHOW");
        }else{
            txt.setVisibility(View.VISIBLE);
            btnToggle.setText("HIDE");
        }
    }


}