package Actor;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.crud_method.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import URL_Requests.CRUD;

public class ActorAdd extends AppCompatActivity {
    ImageView imageView;
    Button btnAdd;
    EditText[] item = new EditText[2]; // creating array variable
    List <String> items = new ArrayList<>();
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_add);

        //using array varaible to set the IDs
        item[0] = findViewById(R.id.actor_add_name);
        item[1] = findViewById(R.id.actor_add_note);

        imageView = findViewById(R.id.actor_add_iv);
        btnAdd = findViewById(R.id.actor_add_btn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = getString(R.string.base_URL)+"api/actor"; //

                //looping through the array variables and adding the string to the arraylist
                for (int i = 0; i < item.length; i++) {
                    items.add(item[i].getText().toString());
                }

                CRUD.Create(ActorAdd.this, items, bitmap, URL);
            }
        });


    }//END OF ONCREATE

    //GETTING BITMAP STRING USING FILE CHOOSER
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
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
    }

    //ON AVTIVITY RESULT NG FILE CHOOSER code:1 at IMAGE CAPTURE code:100
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Picasso.get().load(filePath).fit().centerCrop().into(imageView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }








}