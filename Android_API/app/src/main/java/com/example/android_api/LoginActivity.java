package com.example.android_api;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText etemail, etpassword;
    private Button btnlogin;
    private String LoginURL = "http://192.168.68.103:8000/api/auth/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etemail = (EditText)findViewById(R.id.etemail);
        etpassword = (EditText)findViewById(R.id.etpassword);
        btnlogin = (Button)findViewById(R.id.btnlogin);



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creating json object
                JSONObject jsonItem = new JSONObject();

                //putting datum/data to the newly created json object
                try {
                    jsonItem.put("email", etemail.getText());
                    jsonItem.put("password", etpassword.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //initialize a new request queue instance
                //to prevent multiple request
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,    //kung anong type of request
                        LoginURL,               //url na pupuntahan nung request
                        jsonItem,               //data na ilalagay sa request
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String accessToken = response.getString("access_token");
                                    Toast.makeText(getApplicationContext(), accessToken, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("access_token", accessToken);
                                    startActivity(intent);
                                    finish();
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        (error)->{
                            Toast.makeText(getApplicationContext(), "MAY MALING NANGYAYARE!!", Toast.LENGTH_LONG).show();

                        }
                );

                //adding
                //sending the request
                requestQueue.add(jsonObjectRequest);

            }
        });
    }


}
