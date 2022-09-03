package URL_Requests;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.crud_method.R;

import org.json.JSONException;
import org.json.JSONObject;

import Actor.ActorMain;
import Authentication.LogIn;

//I Createed a user_request class and created method inside it
public class user_request {
    private static SharedPreferences sp;

    //method for login
    public static void user_login(Context mContext, String email, String password){
        sp = mContext.getSharedPreferences("UserCredentials", Context.MODE_PRIVATE);
//        Toast.makeText(mContext, email + " " + password, Toast.LENGTH_SHORT).show();
//        Toast.makeText(mContext, R.string.base_URL, Toast.LENGTH_SHORT).show();
        //creating json object
        JSONObject jsonItem = new JSONObject();

        //putting datum/data to the newly created json object
        try {
            jsonItem.put("email", email);
            jsonItem.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //initialize a new request queue instance
        //to prevent multiple request
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,                                                //kung anong type of request
                mContext.getString(R.string.base_URL)+"api/auth/login",         //url na pupuntahan nung request
                jsonItem,                                                           //data na ilalagay sa request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

//                            //saving credentials using shared preference if checkbox is checked
//                            if(cbRememberMe.isChecked()){
//                                SharedPreferences.Editor editor = sp.edit();
//                                editor.putString("email", etemail.getText().toString());
//                                editor.putString("password", etpassword.getText().toString());
//                                editor.commit();
//                                showToast("ARCHIVE CREDENTIALS", R.drawable.ic_remember);
//                            }

                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("accessToken", response.getString("access_token"));
                            editor.commit();


                            String accessToken = response.getString("access_token");
                            //Toast.makeText(mContext, accessToken, Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(mContext, ActorMain.class);
                            mContext.startActivity(intent);
                            ((LogIn)mContext).finish();

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                (error)->{
                    Toast.makeText(mContext, "MAY MALING NANGYAYARE!!", Toast.LENGTH_LONG).show();

                }
        );

        //adding
        //sending the request
        requestQueue.add(jsonObjectRequest);
    }

}
