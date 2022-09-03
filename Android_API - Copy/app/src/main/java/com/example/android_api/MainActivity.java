package com.example.android_api;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView tvToken;

    private Button btnsearch, btnview, btnsave, btndelete, btnupdate, btnimage;

    private EditText etID, etdesc, etcost, etsell;

    private ImageView imageView;



    private String item_id;
    private URL myURL;
    private String mJSONURLString = "http://192.168.68.103:8000/api/item/";
    private String imgUrl = "http://192.168.68.103:8000/";
    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

    private String accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvToken = (TextView)findViewById(R.id.tvToken);

        imageView = (ImageView)findViewById(R.id.ImageView1);

        etID = (EditText)findViewById(R.id.etID);
        etdesc = (EditText)findViewById(R.id.etdesc);
        etcost = (EditText)findViewById(R.id.etcost);
        etsell = (EditText)findViewById(R.id.etsell);

        btnsearch = (Button)findViewById(R.id.btnsearch);
        btnview = (Button)findViewById(R.id.btnview);
        btnsave = (Button)findViewById(R.id.btnsave);
        btnupdate = (Button)findViewById(R.id.btnupdate);
        btndelete = (Button)findViewById(R.id.btndelete);
        btnimage = (Button)findViewById(R.id.btnimage);

        //getting token from intent extra
        Intent i = getIntent();
        accessToken = i.getStringExtra("access_token");
        //tvToken.setText(accessToken);

        //SEARCH    SEARCH    SEARCH    SEARCH    SEARCH    SEARCH    SEARCH    SEARCH    SEARCH
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creating new url from base URL and ID
                String searchURL = mJSONURLString+etID.getText();

                //checking url
                try {
                    myURL = new URL(searchURL);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                //initialize a new request queue instance
                //to prevent multiple request
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,     //kung anong type of request
                        searchURL,                  //url na pupuntahan nung request
                        null,        //since get method ito, walang data na pinapasa kaya null lang
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    etdesc.setText(response.getString("description"));
                                    etcost.setText(response.getString("cost_price"));
                                    etsell.setText(response.getString("sell_price"));

                                    //Picasso.get().load("https://c8.alamy.com/comp/P1HJ9F/ripe-cherries-on-the-treeimge-of-a-P1HJ9F.jpg").into(imageView);
                                    Picasso.get().load(imgUrl+response.getString("img_path")).into(imageView);

                                    tvToken.setText(imgUrl+response.getString("img_path"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "MAY MALING NANGYAYARE!!", Toast.LENGTH_LONG).show();
                            }
                        }
                ){
                    //ito na ung pag si-set ng authorization token
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError{
                        Map<String, String> params = new HashMap<>();
                        params.put("Authorization", "Bearer "+accessToken);
                        return params;
                    }
                };

                //adding
                //sending the request
                requestQueue.add(jsonObjectRequest);

            }
        });


        //DELETE    DELETE    DELETE    DELETE    DELETE    DELETE    DELETE    DELETE    DELETE
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating new url from base URL and ID
                String searchURL = mJSONURLString+etID.getText();

                //checking url
                try {
                    myURL = new URL(searchURL);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                //initialize a new request queue instance
                //to prevent multiple request
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.DELETE,     //kung anong type of request
                        searchURL,                  //url na pupuntahan nung request
                        null,        //since get method ito, walang data na pinapasa kaya null lang
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                etID.setText("");
                                etdesc.setText("");
                                etcost.setText("");
                                etsell.setText("");
                                Toast.makeText(getApplicationContext(), "BURADO NA!", Toast.LENGTH_LONG).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "MAY MALING NANGYAYARE!!", Toast.LENGTH_LONG).show();
                            }
                        }
                ){
                    //ito na ung pag si-set ng authorization token
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError{
                        Map<String, String> params = new HashMap<>();
                        params.put("Authorization", "Bearer "+accessToken);
                        return params;
                    }
                };

                //adding
                //sending the request
                requestQueue.add(jsonObjectRequest);
            }
        });

        //OPEN GALLERY  OPEN GALLERY    OPEN GALLERY    OPEN GALLERY    OPEN GALLERY    OPEN GALLERY
        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        //SAVE  SAVE    SAVE    SAVE    SAVE    SAVE    SAVE    SAVE    SAVE    SAVE    SAVE    SAVE
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //creating json object
                JSONObject jsonItem = new JSONObject();

                //putting datum/data to the newly created json object
                try {
                    jsonItem.put("description", etdesc.getText());
                    jsonItem.put("sell_price", etsell.getText());
                    jsonItem.put("cost_price", etcost.getText());
                    jsonItem.put("uploads", getStringImage(bitmap));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //checking url
                try {
                    myURL = new URL(mJSONURLString);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                //initialize a new request queue instance
                //to prevent multiple request
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,     //kung anong type of request
                        mJSONURLString,                  //url na pupuntahan nung request
                        jsonItem,                   //data na ilalagay sa request
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                etID.setText("");
                                etdesc.setText("");
                                etcost.setText("");
                                etsell.setText("");
                                Toast.makeText(getApplicationContext(), "NA SAVE NA PRE", Toast.LENGTH_LONG).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "MAY MALING NANGYAYARE!!", Toast.LENGTH_LONG).show();
                            }
                        }
                ){
                    //ito na ung pag si-set ng authorization token
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError{
                        Map<String, String> params = new HashMap<>();
                        params.put("Authorization", "Bearer "+accessToken);
                        return params;
                    }
                };

                //adding
                //sending the request
                requestQueue.add(jsonObjectRequest);

            }
        });

        //UPDATE    UPDATE  UPDATE  UPDATE  UPDATE  UPDATE  UPDATE  UPDATE  UPDATE  UPDATE  UPDATE
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,bitmap.toString(), Toast.LENGTH_SHORT).show();
//                //creating new url from base URL and ID
//                String updateURL = mJSONURLString+etID.getText();
//
//                //creating json object
//                JSONObject jsonItem = new JSONObject();
//
//                //putting datum/data to the newly created json object
//                try {
//                    jsonItem.put("description", etdesc.getText());
//                    jsonItem.put("sell_price", etsell.getText());
//                    jsonItem.put("cost_price", etcost.getText());
//                    jsonItem.put("uploads", getStringImage(bitmap));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                //checking url
//                try {
//                    myURL = new URL(updateURL);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//
//                //initialize a new request queue instance
//                //to prevent multiple request
//                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                        Request.Method.PUT,     //kung anong type of request
//                        updateURL,                  //url na pupuntahan nung request
//                        jsonItem,                   //data na ilalagay sa request
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                etID.setText("");
//                                etdesc.setText("");
//                                etcost.setText("");
//                                etsell.setText("");
//                                Toast.makeText(getApplicationContext(), "NA SAVE NA PRE", Toast.LENGTH_LONG).show();
//
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(getApplicationContext(), "MAY MALING NANGYAYARE!!", Toast.LENGTH_LONG).show();
//                            }
//                        }
//                ){
//                    //ito na ung pag si-set ng authorization token
//                    @Override
//                    public Map<String, String> getHeaders() throws AuthFailureError{
//                        Map<String, String> params = new HashMap<>();
//                        params.put("Authorization", "Bearer "+accessToken);
//                        return params;
//                    }
//                };
//
//                //adding
//                //sending the request
//                requestQueue.add(jsonObjectRequest);
            }
        });

        //VIEW ALL DATA     VIEW ALL DATA     VIEW ALL DATA     VIEW ALL DATA     VIEW ALL DATA     
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("access_token", accessToken);
                startActivity(intent);
            }
        });

    }

    //GETTING BITMAP STRING
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    //FILE CHOOSER METHOD | GALLERY BY DEFAULT
    private void showFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE_REQUEST);
    }
    //ON AVTIVITY RESULT NG FILE CHOOSER
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                //Picasso.get().load(bitmap.into(imageView);
                //Picasso.get().load(filePath).resize(100, 100).
                Picasso.get().load(filePath).fit().centerCrop().into(imageView);
                //imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}