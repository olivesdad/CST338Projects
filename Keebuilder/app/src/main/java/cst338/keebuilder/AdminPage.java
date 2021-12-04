package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AdminPage extends AppCompatActivity {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        wireup();
    }

    public void wireup(){
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Admin Options");
    }

     public static Intent getTntent(Context c, int userId){
        Intent intent = new Intent(c, AdminPage.class);
        intent.putExtra(DBtools.USER_ID, userId);
        return intent;
     }
}