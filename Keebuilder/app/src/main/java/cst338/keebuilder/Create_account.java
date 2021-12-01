package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Create_account extends AppCompatActivity {
    Button create_account_create_account_button;
    EditText create_account_password_input1;
    EditText create_account_password_input2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //Action bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Account Creation");

        //connect buttons
        create_account_create_account_button = findViewById(R.id.create_account_create_acount_button);
        create_account_password_input1 = findViewById(R.id.create_account_password_input);
        create_account_password_input2 = findViewById(R.id.create_account_password_input2);
    }

    //intent factory
    public static Intent getIntent(Context context){
        return new Intent(context, Create_account.class);
    }
}