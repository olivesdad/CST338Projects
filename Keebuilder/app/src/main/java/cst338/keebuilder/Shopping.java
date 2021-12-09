package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cst338.keebuilder.db.KeebDao;

public class Shopping extends AppCompatActivity {
    Button addToCart, goToCart;
    TextView shoppingBanner;
    TextView inventory;
    ActionBar ab;
    KeebDao kd;
    EditText itemInput, itemQty;
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
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
                itemInput.setText("");
                itemQty.setText("");
            }
        });
        goToCart = findViewById(R.id.Shopping_go_to_cart_button);
        itemInput = findViewById(R.id.Shopping_Item_input_text);
        itemQty = findViewById(R.id.Shopping_Item_qty_input_text);
        inventory = findViewById(R.id.Instock_list);
        inventory.setMovementMethod(new ScrollingMovementMethod());
        shoppingBanner = findViewById(R.id.Shopping_inventory_label);
        ab=getSupportActionBar();
        ab.setTitle("Welcome to the shop " + user.getMUserName());
        ab.setDisplayHomeAsUpEnabled(true);
        refreshDisplay();
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

    private void addToCart(){
        String itemName = itemInput.getText().toString().toLowerCase();
        String me = user.getMUserName();
        int qty = 0;
        try {
             qty = Integer.parseInt(itemQty.getText().toString());
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalit Quantity", Toast.LENGTH_SHORT).show();
            return;
        }
        StoreItem item = kd.getStoreItemByName(itemName);
        if (!(item == null)){
            //check the qty is ok
            if (qty > 0 && qty < item.getMQty()){
                //deduct the qty from item
                item.setMQty(item.getMQty() - qty);
                //check for dupe item
                CartItem dupe = kd.getCartItemByName(itemName);
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
                refreshDisplay();
                Toast.makeText(getApplicationContext(), item.getMDisplayName() + " added to cart", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), itemName + " not found", Toast.LENGTH_SHORT).show();
        }

    }
    public static Intent getIntent(Context c){
        return new Intent(c, Shopping.class);
    }
}