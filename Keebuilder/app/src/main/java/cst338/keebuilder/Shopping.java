package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cst338.keebuilder.db.KeebDao;

public class Shopping extends AppCompatActivity {
    Button addToCart, goToCart;
    TextView shoppingBanner;
    TextView inventory;
    ActionBar ab;
    KeebDao kd;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        kd = DBtools.getKeebDao(this);
        user = DBtools.getActiveUser(this);
        wireUp();
    }

    private void wireUp(){
        addToCart = findViewById(R.id.Shopping_add_to_cart_button);
        goToCart = findViewById(R.id.Shopping_go_to_cart_button);
        inventory = findViewById(R.id.Instock_list);
        shoppingBanner = findViewById(R.id.Shopping_inventory_label);
        ab=getSupportActionBar();
        ab.setTitle("Welcome to the shop " + user.getMUserName());
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public static Intent getIntent(Context c){
        return new Intent(c, Shopping.class);
    }
}