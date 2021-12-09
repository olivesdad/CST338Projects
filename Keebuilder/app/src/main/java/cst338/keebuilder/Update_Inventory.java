package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Update;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cst338.keebuilder.db.KeebDao;

public class Update_Inventory extends AppCompatActivity {
    EditText nameInput, descInput, categoryInput, qtyInput, priceInput;
    Button UpdateItemButton;
    KeebDao kd;
    ActionBar ab;
    TextView inventory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inventory);
        kd = DBtools.getKeebDao(this);
        ab = getSupportActionBar();
        wireUp();
        refreshDisplay();
    }

    private void wireUp(){
        nameInput = findViewById(R.id.Update_items_item_name_input);
        descInput = findViewById(R.id.Update_items_description_input);
        categoryInput = findViewById(R.id.Update_item_category);
        qtyInput = findViewById(R.id.Update_Quantity);
        inventory = findViewById(R.id.Update_inventory_list);
        inventory.setMovementMethod(new ScrollingMovementMethod());
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("");
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        priceInput = findViewById(R.id.Update_item_price);
        UpdateItemButton = findViewById(R.id.Update_items_button);
        UpdateItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItem();
                nameInput.setText("");
                descInput.setText("");
                categoryInput.setText("");
                qtyInput.setText("");
                priceInput.setText("");
                refreshDisplay();
            }
        });
    }

    private void refreshDisplay(){
        StringBuilder sb = new StringBuilder();
        List<StoreItem> items = kd.getAllStoreItems();
        for (StoreItem item : items){
            sb.append(item.getMDisplayName().toString()+ "\n" +
                      "Description: " + item.getMDescription() + "\n" +
                        "Category: " + item.getMCategory() + "\n" +
                        "Qty: " + item.getMQty() + "\n" +
                        "Price: $" + String.format("%.2f",item.getMPrice()) + "\n" +
                        ".....-----~~~~~=====XXX=====~~~~~-----....." + "\n");
        }
        inventory.setText(sb);
    }

    private void updateItem(){
        String itemName, displayName, itemDesc, category, sQty, sPrice;
        double price;
        StoreItem item;
        int qty;
        boolean updated = false;

        displayName = nameInput.getText().toString();
        itemName = displayName.toLowerCase();
        item = kd.getStoreItemByName(itemName);
        if(item == null){
            Toast.makeText(getApplicationContext(), itemName +" not found in inventory. Please use Add Item.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!displayName.equals(item.getMDisplayName())) {
            item.setMDisplayName(displayName); //set new display name
            updated = true;
        }
        //get content of text boxes
        itemDesc = descInput.getText().toString();
        category = categoryInput.getText().toString();
        sQty = qtyInput.getText().toString();
        sPrice = priceInput.getText().toString();
        if (!itemDesc.equals("")){
            //update description
            item.setMDescription(itemDesc);
            updated = true;
        }
        if (!category.equals("")){
            //update category
            item.setMCategory(category);
            updated = true;
        }
        if (!sQty.equals("")){
            //update qty
            try{
                qty = Integer.parseInt(sQty); //convert string to integer
                if(qty <= 0){ //if the new qty is less than or equal to 0 delete item, toast, and return
                    kd.delete(item);
                    Toast.makeText(getApplicationContext(), displayName+ " has been removed from inventory", Toast.LENGTH_SHORT).show();
                    return;
                }else{ //qty is a positive number just update item
                    item.setMQty(qty);
                    updated = true;
                }
            } catch  (NumberFormatException e){ //catch non integer
                Toast.makeText(getApplicationContext(), "Invalid Quantity", Toast.LENGTH_SHORT).show();
            }
        }
        if (!sPrice.equals("")){
            //update price
            try {
                price = Double.parseDouble(sPrice);
                item.setMPrice(price);
                updated = true;
            }catch(NumberFormatException e){
                Toast.makeText(getApplicationContext(), "Invalid Price", Toast.LENGTH_SHORT).show();
            }
        }
        if (updated) kd.update(item);

    }

    public static Intent getIntent(Context c){
        return new Intent(c, Update_Inventory.class);
    }


}