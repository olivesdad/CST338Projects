package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.file.attribute.AclEntryType;
import java.util.List;

import cst338.keebuilder.db.KeebDao;

public class Edit_Users extends AppCompatActivity {
    KeebDao kd;
    User user;
    ActionBar ab;
    TextView userList, userLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_users);
         kd = DBtools.getKeebDao(this);
        user = DBtools.getActiveUser(this);

        wireUp();
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

    private void wireUp(){
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Manage Users");
        userList = findViewById(R.id.Edit_user_list);
        userLabel = findViewById(R.id.Edit_user_list_label);
        userList.setMovementMethod(new ScrollingMovementMethod());
        refreshUsers();
    }

    public static Intent getIntent(Context context){
        return new Intent(context, Edit_Users.class);
    }
}