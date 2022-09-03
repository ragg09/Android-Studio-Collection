package URL_Requests;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.crud_method.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Actor.ActorAdapter;
import Actor.ActorAdd;
import Actor.ActorEdit;
import Actor.ActorModel_Index;
import Authentication.LogIn;

public class CRUD {
    private static URL myURL;
    private static SharedPreferences sp;

    //INDEX     INDEX     INDEX     INDEX     INDEX     INDEX     INDEX     INDEX     INDEX     INDEX
    public static void actor_index(Context mContext, RecyclerView actormain_rv){
        List<ActorModel_Index> actor_list_data;
        ActorAdapter adapter;


        //SETTING RV, actor_list_dat, and adapter
        actormain_rv.setHasFixedSize(true);
        actormain_rv.setLayoutManager(new LinearLayoutManager(mContext));
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        actormain_rv.addItemDecoration(divider);
        actor_list_data = new ArrayList<>();
        adapter = new ActorAdapter(mContext,actor_list_data);

        //Log.i("url","url"+ myURL);
        //RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        // stringRequest=new StringRequest(Request.Method.GET,
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                mContext.getString(R.string.base_URL)+"api/actor",
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("data");
                            for (int i=0; i<array.length(); i++ ){
                                JSONObject ob = array.getJSONObject(i);
                                ActorModel_Index listData = new ActorModel_Index(
                                        ob.getInt("id"),
                                        ob.getString("name"),
                                        ob.getString("note"),
                                        ob.getString("image")
                                );
                                actor_list_data.add(listData);

                            }

                            actormain_rv.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+ getAccessToken(mContext));
                return params;
            }


        };
        RequestQueue requestQueue= Volley.newRequestQueue(mContext);
        requestQueue.add(jsonRequest);
    }
    //^^^INDEX     INDEX     INDEX     INDEX     INDEX     INDEX     INDEX     INDEX     INDEX     INDEX

    //ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD
    public static void Create(Context mContext, List<String> items, Bitmap bitmap, String addURL){
        //creating json object
        JSONObject jsonItem = new JSONObject();

        //putting datum/data to the newly created json object
//        try {
//            jsonItem.put("name"+0, items.get(0));
//            jsonItem.put("note"+1, items.get(1));
//            jsonItem.put("uploads", getStringImage(bitmap));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        //looping through the length of the list and setting the object that is going to the backend
        try {
            for (int i = 0; i < items.size(); i++) {
                jsonItem.put("item"+i, items.get(i));
            }
            jsonItem.put("uploads", getStringImage(bitmap));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //checking url
        try {
            myURL = new URL(addURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //initialize a new request queue instance
        //to prevent multiple request
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,                                            //kung anong type of request
                addURL,                  //url na pupuntahan nung request
                jsonItem,                                                       //data na ilalagay sa request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ((ActorAdd)mContext).finish();
                        Toast.makeText(mContext, "SAVED PARE", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "MAY MALING NANGYAYARE!!", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            //ito na ung pag si-set ng authorization token
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer "+getAccessToken(mContext));
                return params;
            }
        };

        //adding
        //sending the request
        requestQueue.add(jsonObjectRequest);

    }
    //^^^ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD   ADD

    //UPDATE    UPDATE    UPDATE    UPDATE    UPDATE    UPDATE    UPDATE    UPDATE    UPDATE    UPDATE
    public static void Update(Context mContext, List<String> items, Bitmap bitmap,String verBitmap, String updateURL){
        //creating json object
        JSONObject jsonItem = new JSONObject();

        //putting datum/data to the newly created json object
        try {
            for (int i = 0; i < items.size(); i++) {
                jsonItem.put("item"+i, items.get(i));
            }

            if(verBitmap == ""){
                jsonItem.put("uploads", "");
            }else{
                jsonItem.put("uploads", getStringImage(bitmap));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //checking url
        try {
            myURL = new URL(updateURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //initialize a new request queue instance
        //to prevent multiple request
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT,     //kung anong type of request
                updateURL,                  //url na pupuntahan nung request
                jsonItem,                   //data na ilalagay sa request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ((ActorEdit)mContext).finish();
                        Toast.makeText(mContext, "UPDATED NA PARE", Toast.LENGTH_SHORT).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "MAY MALING NANGYAYARE", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            //ito na ung pag si-set ng authorization token
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer "+getAccessToken(mContext));
                return params;
            }
        };

        //adding
        //sending the request
        requestQueue.add(jsonObjectRequest);
    }
    //^^^UPDATE    UPDATE    UPDATE    UPDATE    UPDATE    UPDATE    UPDATE    UPDATE    UPDATE    UPDATE

    //DELETE    DELETE    DELETE    DELETE    DELETE    DELETE    DELETE    DELETE    DELETE    DELETE
    public static void Delete(Context mContext, String deleteURL){

        //checking url
        try {
            myURL = new URL(deleteURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //initialize a new request queue instance
        //to prevent multiple request
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.DELETE,     //kung anong type of request
                deleteURL,                  //url na pupuntahan nung request
                null,        //since get method ito, walang data na pinapasa kaya null lang
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(mContext, "DELETED NA", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "MAY MALING NANGYAYARE", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            //ito na ung pag si-set ng authorization token
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer "+getAccessToken(mContext));
                return params;
            }
        };

        //adding
        //sending the request
        requestQueue.add(jsonObjectRequest);
    }
    //^^^DELETE    DELETE    DELETE    DELETE    DELETE    DELETE    DELETE    DELETE    DELETE    DELETE

    //GETTING BITMAP STRING USING FILE CHOOSER
    public static String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    //GETTING SHARED PREF
    public static String getAccessToken(Context mContext){
        sp = mContext.getSharedPreferences("UserCredentials", Context.MODE_PRIVATE);
        String accessToken = sp.getString("accessToken", "");
        return accessToken;
    }




}
