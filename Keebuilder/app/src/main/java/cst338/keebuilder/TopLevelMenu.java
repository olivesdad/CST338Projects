package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cst338.keebuilder.db.KeebDao;

public class TopLevelMenu extends AppCompatActivity {
    Button logout;
    ActionBar actionBar;
    User user=null;
    KeebDao kd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level_menu);
        //get dao
        kd = DBtools.getKeebDao(getApplicationContext());
        //get current user
        user = kd.getUserById(getIntent().getIntExtra(DBtools.USER_ID, -1));
        //wire up display stuff
        wireUp();
    }

    private void wireUp() {
        //buttons and stuff
        logout = findViewById(R.id.topLevelMenuLogout);
        actionBar = getSupportActionBar();
        //set top bar thing
        if(user != null){
            StringBuilder title = new StringBuilder();
            title.append(user.getMUserName().toString());
            if (user.isMIsAdmin()){
                title.append(" (Admin)");
            }
            actionBar.setTitle(title);
        }else{
            actionBar.setTitle("OMG HACKER!");
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.getMainIntent(getApplicationContext()));
            }
        });
    }

    public static Intent getTopLevelMenuIntent(Context context, int userId){
        Intent intent = new Intent(context, TopLevelMenu.class);
        intent.putExtra(DBtools.USER_ID, userId);
        return intent;
    }
}