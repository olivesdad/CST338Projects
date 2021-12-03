package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cst338.keebuilder.db.AppDatabase;
import cst338.keebuilder.db.KeebDao;

public class MainActivity extends AppCompatActivity {
    Button login;
    KeebDao keebdao;
    Button create_account;
    EditText user_name_text_input;
    EditText password_text_input;
    KeebDao keebDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keebDao = DBtools.getKeebDao(getApplicationContext());
        wireUp();

    }

    private void wireUp(){
        //buttons
        user_name_text_input = findViewById(R.id.user_name_input);
        password_text_input = findViewById(R.id.password_input);
        create_account = findViewById(R.id.login_create_acount_button);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Create_account.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
        login = findViewById(R.id.login_login_button);
        //Action bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Account Login");

    }
    private void login(String name, String pw){

    }
    public static Intent getMainIntent(Context context){
        return new Intent(context, MainActivity.class);
    }
}