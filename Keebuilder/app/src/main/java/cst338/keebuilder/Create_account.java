package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cst338.keebuilder.db.KeebDao;

public class Create_account extends AppCompatActivity {
    Button create_account_create_account_button;
    EditText user_name_input_txt;
    EditText create_account_password_input1;
    EditText create_account_password_input2;
    KeebDao keebDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        //get dao
        keebDao = DBtools.getKeebDao(getApplicationContext());
        wireUp();

    }

    private void wireUp(){
        //Action bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Account Creation");
        //connect buttons
        create_account_create_account_button = findViewById(R.id.create_account_create_acount_button);
        create_account_password_input1 = findViewById(R.id.create_account_password_input);
        create_account_password_input2 = findViewById(R.id.create_account_password_input2);
        user_name_input_txt = findViewById(R.id.user_name_input);
        //connect method for create
        create_account_create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser(user_name_input_txt.getText().toString(),
                        create_account_password_input1.getText().toString(),
                        create_account_password_input2.getText().toString());
                user_name_input_txt.setText("");
                create_account_password_input1.setText("");
                create_account_password_input2.setText("");
            }
        });
    }

    private void addUser(String name, String pw1, String pw2){
        //make sure user doesnt exist
        if (keebDao.getUserByName(name)!= null){
            Toast.makeText(this, "Username taken", Toast.LENGTH_SHORT).show();
            return;
        }
        //compare passwords
        if(!pw1.equals(pw2)){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }
        //add user
        keebDao.insert(new User(name, pw1, false));
        Toast.makeText(this, "User added to system",Toast.LENGTH_SHORT).show();

    }
    //intent factory
    public static Intent getIntent(Context context){
        return new Intent(context, Create_account.class);
    }
}