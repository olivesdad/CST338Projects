package cst338.keebuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import cst338.keebuilder.db.KeebDao;

public class Add_store_items extends AppCompatActivity {
    EditText nameInput, descInput, categoryInput, qtyInput, priceInput;
    Button addItemButton;
    KeebDao kd;
    User user;
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
        priceInput = findViewById(R.id.Add_item_price);
        addItemButton = findViewById(R.id.Add_items_button);
    }
    public static Intent getIntent(Context context){
        return new Intent(context, Add_store_items.class);
    }
}