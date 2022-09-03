package Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud_method.R;

import URL_Requests.user_request;

public class LogIn extends AppCompatActivity {
    EditText login_et_username, login_et_password;
    Button login_btn;
    //user_request req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        login_et_username = findViewById(R.id.login_et_username);
        login_et_password = findViewById(R.id.login_et_password);
        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_et_username.getText().toString();
                String password = login_et_password.getText().toString();

                //import the class, then call the method
               user_request.user_login(LogIn.this, email, password);

            }
        });



    }//ONCREATE END
}