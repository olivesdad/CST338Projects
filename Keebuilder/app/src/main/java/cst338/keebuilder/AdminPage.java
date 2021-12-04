package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.List;

import cst338.keebuilder.db.KeebDao;

public class AdminPage extends AppCompatActivity {
    ActionBar actionBar;
    TextView userList, userListLabel;
    KeebDao kd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        kd=DBtools.getKeebDao(this);
        wireup();
        refreshUsers();
    }

    public void wireup(){
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Admin Options");
        userList = findViewById(R.id.AdminUserListDisplay);
        userListLabel = findViewById(R.id.Admin_user_list_label);
        userList.setMovementMethod(new ScrollingMovementMethod());
    }

     public static Intent getTntent(Context c, int userId){
        Intent intent = new Intent(c, AdminPage.class);
        intent.putExtra(DBtools.USER_ID, userId);
        return intent;
     }
     public void refreshUsers(){
         List<User> users = kd.getAllUsers();
         StringBuilder sb = new StringBuilder();
         for (User user : users){
             sb.append("Username: " + user.getMUserName().toString() +"\n");
             sb.append("Admin: ");
             if (user.isMIsAdmin()) sb.append("Yes");
             else sb.append("No");
             sb.append("\n.....----======XXXXXXXX======----.....\n ");
         }
         userList.setText(sb);
     }
}