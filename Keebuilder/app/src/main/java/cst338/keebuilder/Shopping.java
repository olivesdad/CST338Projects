package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cst338.keebuilder.db.KeebDao;

public class Shopping extends AppCompatActivity {
    Button addToCart, goToCart;
    TextView shoppingBanner;
    TextView inventory;
    Spinner dd;
    ActionBar ab;
    KeebDao kd;
    EditText itemInput, itemQty;
    User user;
    ArrayAdapter<String> adapter;
    List<String> categories, shownItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categories = new ArrayList<>();
        setContentView(R.layout.activity_shopping);
        kd = DBtools.getKeebDao(this);
        user = DBtools.getActiveUser(this);
        wireUp();

    }

    private void wireUp(){
       categories.add("ALL");
       dd = findViewById(R.id.Catagory_Filter_Spinner);
       shownItems = new ArrayList<>();
       //create spinner adapter of ArrayAdapter type String
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dd.setAdapter(adapter);
        updateSpinner();
        dd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                refreshDisplay(dd.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addToCart = findViewById(R.id.Shopping_add_to_cart_button);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
                itemInput.setText("");
                itemQty.setText("");
                updateSpinner();
            }
        });
        goToCart = findViewById(R.id.Shopping_go_to_cart_button);
        goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Cart.getIntent(getApplicationContext()));
            }
        });
        itemInput = findViewById(R.id.Shopping_Item_input_text);
        itemQty = findViewById(R.id.Shopping_Item_qty_input_text);
        inventory = findViewById(R.id.Instock_list);
        inventory.setMovementMethod(new ScrollingMovementMethod());
        shoppingBanner = findViewById(R.id.Shopping_inventory_label);
        ab=getSupportActionBar();
        ab.setTitle("Welcome to the shop " + user.getMUserName());
        ab.setDisplayHomeAsUpEnabled(true);
        refreshDisplay(dd.getSelectedItem().toString());
    }
    private void updateSpinner()
    {
        adapter.clear();
        List<String> temp = new ArrayList<>();
        temp.add("ALL");
        List<StoreItem> items = kd.getAllStoreItems();
        for (StoreItem item : items){
           if (!temp.contains(item.getMCategory())){
               temp.add(item.getMCategory());
           }
        }
        adapter.addAll(temp);

    }
    private void refreshDisplay(String filter){
        StringBuilder sb = new StringBuilder();
        int num = 1;
        shownItems.clear();
        List<StoreItem> items = kd.getAllStoreItems();
        for (StoreItem item : items){
            if (filter.equals("ALL") || filter.equals(item.getMCategory())) {
                shownItems.add(item.getMItemName());
                sb.append(num++ + ") " + item.getMDisplayName().toString() + "\n" +
                        "Description: " + item.getMDescription() + "\n" +
                        "Category: " + item.getMCategory() + "\n" +
                        "Qty: " + item.getMQty() + "\n" +
                        "Price: $" + String.format("%.2f", item.getMPrice()) + "\n" +
                        ".....-----~~~~~=====XXX=====~~~~~-----....." + "\n");
            }
        }
        inventory.setText(sb);
    }

    private void addToCart(){
        String itemDisplayname;
        try {
             itemDisplayname = shownItems.get(Integer.parseInt(itemInput.getText().toString().trim()) - 1);
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid number", Toast.LENGTH_SHORT).show();
            return;
        }catch (IndexOutOfBoundsException e){
            Toast.makeText(getApplicationContext(), "Invalid item number", Toast.LENGTH_SHORT).show();
            return;
        }
        String itemName = itemDisplayname.toLowerCase();
        String me = user.getMUserName();
        int qty = 0;
        try {
             qty = Integer.parseInt(itemQty.getText().toString());
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Quantity", Toast.LENGTH_SHORT).show();
            return;
        }
        StoreItem item = kd.getStoreItemByName(itemName);
        if (item != null){
            //check the qty is ok
            if (qty > 0 && qty <= item.getMQty()){
                //deduct the qty from item
                item.setMQty(item.getMQty() - qty);
                //check for dupe item
                CartItem dupe = kd.getCartItemByNameAndUser(itemName, user.getMUserName());
                if (dupe == null) {
                    //construct new cart item
                    CartItem cartItem = new CartItem(item.getMDisplayName(), item.getMCategory(), user.getMUserName(), qty, item.getMPrice());
                    //add cart item to cart item DB
                    kd.insert(cartItem);
                } else {
                    dupe.setCQty(dupe.getCQty() + qty);
                    kd.update(dupe);
                }
                //return item back to db
                kd.update(item);
                refreshDisplay(dd.getSelectedItem().toString());
                Toast.makeText(getApplicationContext(), item.getMDisplayName() + " added to cart", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Unable to fulfill request", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), itemName + " not found", Toast.LENGTH_SHORT).show();
        }

    }
    public static Intent getIntent(Context c){
        return new Intent(c, Shopping.class);
    }
}