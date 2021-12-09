package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cst338.keebuilder.db.AppDatabase;
import cst338.keebuilder.db.KeebDao;

public class TopLevelMenu extends AppCompatActivity {
    Button logout, goToShop;
    Button admin_button;
    ActionBar actionBar;
    User user=null;
    KeebDao kd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level_menu);
        //get dao
        kd = DBtools.getKeebDao(getApplicationContext());
        //set shared pref with the user id
        setPref(getIntent().getIntExtra(DBtools.USER_ID,-1));
        user = DBtools.getActiveUser(this);
        //wire up display stuff
        wireUp();

    }

    private void wireUp() {
        //buttons and stuff
        logout = findViewById(R.id.topLevelMenuLogout);
        goToShop = findViewById(R.id.topLevelMenuGoShoppingButton);
        admin_button = findViewById(R.id.Top_level_admin_button);
        admin_button.setVisibility(View.GONE);
        actionBar = getSupportActionBar();

        //set top bar thing
        if(user != null){
            StringBuilder title = new StringBuilder(); //builder for top bar
            title.append(user.getMUserName().toString()); //starts with user name
            if (user.isMIsAdmin()){
                title.append(" (Admin)"); //if the user is admin add to name
                admin_button.setVisibility(View.VISIBLE);
                admin_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    startActivity(AdminPage.getTntent(getApplicationContext(), user.getMUserId()));
                    }
                });
            }
            actionBar.setTitle(title);
        }else{
            actionBar.setTitle("OMG HACKER!");
        }
        //logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPref(-1); // set the shared preference user id back to -1
                startActivity(MainActivity.getMainIntent(getApplicationContext())); //go back to Main
            }
        });
        goToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Shopping.getIntent(getApplicationContext()));
            }
        });
    }

    //use this to store userId of logged in user within sharepreference
    private void setPref(int userId){
        SharedPreferences sharedPref = getSharedPreferences(DBtools.SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(DBtools.USER_ID, userId);
        editor.apply();
    }
    //intent factory takes userID
    public static Intent getTopLevelMenuIntent(Context context, int userId){
        Intent intent = new Intent(context, TopLevelMenu.class);
        intent.putExtra(DBtools.USER_ID, userId);
        return intent;
    }
}