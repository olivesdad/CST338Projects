package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cst338.keebuilder.db.AppDatabase;
import cst338.keebuilder.db.KeebDao;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button create_account;
    EditText user_name_text_input;
    EditText password_text_input;
    KeebDao kd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kd = DBtools.getKeebDao(getApplicationContext());
        checkPrefs();
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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(user_name_text_input.getText().toString(), password_text_input.getText().toString());
            }
        });
        //Action bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Account Login");

    }
    private void login(String name, String pw){ //login
        User newLogin = kd.getUserByName(name); //get user by username
        if (newLogin == null || !newLogin.getMUserPassword().toString().equals(pw)){ //if user doesnt exist or password doesnt match
            Toast.makeText(this, "Bad Username or Password", Toast.LENGTH_SHORT).show(); //Toasty!
        } else { //if user login successful start toplevel intent with userID
            Intent intent = TopLevelMenu.getTopLevelMenuIntent(getApplicationContext(), newLogin.getMUserId());
            startActivity(intent);
        }
    }

    public void checkPrefs(){ //Check if there is a userID stored in sharedPreference and log them in if so
        SharedPreferences sharedPref = getSharedPreferences(DBtools.SP, Context.MODE_PRIVATE);
        int userId = sharedPref.getInt(DBtools.USER_ID, -1);
        if(userId != -1){
            Intent intent = TopLevelMenu.getTopLevelMenuIntent(getApplicationContext(), userId);
            startActivity(intent);
        }

    }
    public static Intent getMainIntent(Context context){ //intent factory
        return new Intent(context, MainActivity.class);
    }
}