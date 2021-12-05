package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.attribute.AclEntryType;
import java.util.List;
import java.util.Locale;

import cst338.keebuilder.db.KeebDao;

public class Edit_Users extends AppCompatActivity {
    KeebDao kd;
    User user;
    ActionBar ab;
    Button deleteUserButton, promoteUserButton;
    TextView userList, userLabel;
    EditText userNameInputText;
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
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
        promoteUserButton = findViewById(R.id.Promote_user_button);
        ab.setTitle("Manage Users");
        userList = findViewById(R.id.Edit_user_list);
        userNameInputText = findViewById(R.id.edit_users_delete_user_input_text);
        userLabel = findViewById(R.id.Edit_user_list_label);
        userList.setMovementMethod(new ScrollingMovementMethod());
        deleteUserButton = findViewById(R.id.Edit_user_delete_button);
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userNameInputText.getText().toString();
                if (name.equals(user.getMUserName().toLowerCase(Locale.ROOT))){
                    Toast.makeText(getApplicationContext(), "Can not delete active user", Toast.LENGTH_SHORT).show();
                } else if (DBtools.deleteUserByUserName(name, kd)){
                    Toast toast = Toast.makeText(getApplicationContext(), "User Deleted", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 50);
                    toast.show();
                    userNameInputText.setText("");
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Unable to find user", Toast.LENGTH_SHORT);
                    toast.show();
                }
                refreshUsers();
            }
        });

        promoteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userNameInputText.getText().toString();
                if (name.equals(user.getMUserName().toString())){
                    Toast.makeText(getApplicationContext(), "Can not modify active user", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (DBtools.changeAdminStatus(name , kd)){
                    Toast.makeText(getApplicationContext(), name +" is an admin", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), name + " is not an admin", Toast.LENGTH_SHORT ).show();
                }
                userNameInputText.setText("");
                refreshUsers();
            }
        });
        refreshUsers();

    }

    public static Intent getIntent(Context context){
        return new Intent(context, Edit_Users.class);
    }
}