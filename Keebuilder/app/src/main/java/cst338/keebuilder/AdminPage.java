package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cst338.keebuilder.db.KeebDao;

public class AdminPage extends AppCompatActivity {
    ActionBar actionBar;
    Button editUsersButton;
    KeebDao kd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        kd=DBtools.getKeebDao(this);
        wireup();
    }

    public void wireup(){
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Admin Options");
        editUsersButton = findViewById(R.id.Admin_Manage_Users_Button);
        editUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Edit_Users.getIntent(getApplicationContext()));
            }
        });
    }

     public static Intent getTntent(Context c, int userId){
        Intent intent = new Intent(c, AdminPage.class);
        intent.putExtra(DBtools.USER_ID, userId);
        return intent;
     }

}