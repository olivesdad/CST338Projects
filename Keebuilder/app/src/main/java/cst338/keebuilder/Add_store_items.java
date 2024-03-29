package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import cst338.keebuilder.db.KeebDao;

public class Add_store_items extends AppCompatActivity {
    EditText nameInput, descInput, categoryInput, qtyInput, priceInput;
    Button addItemButton;
    KeebDao kd;
    User user;
    ActionBar ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kd = DBtools.getKeebDao(this);
        user = DBtools.getActiveUser(this);
        setContentView(R.layout.activity_add_store_items);
        wireUp();
    }
    private void wireUp(){
        nameInput = findViewById(R.id.Add_items_item_name_input);
        descInput = findViewById(R.id.Add_items_description_input);
        categoryInput = findViewById(R.id.Add_item_category);
        qtyInput = findViewById(R.id.Quantity);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("");
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        priceInput = findViewById(R.id.Add_item_price);
        addItemButton = findViewById(R.id.Add_items_button);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItems();
            }
        });
    }

    private void addItems(){
        String name,displayName, desc, category;
        int qty;
        double price;
        //get strings
        displayName = nameInput.getText().toString();
        name = displayName.toLowerCase();
        desc = descInput.getText().toString();
        category = categoryInput.getText().toString().toLowerCase();
        try {
            qty = Integer.parseInt(qtyInput.getText().toString());
            price = Double.parseDouble(priceInput.getText().toString());
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Number", Toast.LENGTH_SHORT).show();
            return;
        }
        //see if there is already an entry
        if(!(kd.getStoreItemByName(name)==null)){
            Toast.makeText(getApplicationContext(), name + " already exists. Please use update item instead.", Toast.LENGTH_SHORT).show();
        } else if ( qty <=0 ){
            Toast.makeText(getApplicationContext(), "Invalid quantity", Toast.LENGTH_SHORT).show();
        }
        else {
            StoreItem item = new StoreItem(name,displayName, desc, category, qty, price);
            kd.insert(item);
            Toast.makeText(getApplicationContext(), name + " successfully added to store.", Toast.LENGTH_SHORT).show();
        }
        nameInput.setText("");
        descInput.setText("");
        categoryInput.setText("");
        qtyInput.setText("");
        priceInput.setText("");
    }
    public static Intent getIntent(Context context){
        return new Intent(context, Add_store_items.class);
    }
}