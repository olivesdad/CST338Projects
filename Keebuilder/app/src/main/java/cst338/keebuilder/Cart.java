package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cst338.keebuilder.db.KeebDao;

public class Cart extends AppCompatActivity {

    TextView CartBanner, cartContents;
    EditText updateNum, newQty;
    Button updateQty;
    ActionBar ab;
    User user;
    KeebDao kd;
    Double total;
    List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        total = 0.0;
        user = DBtools.getActiveUser(this);
        kd = DBtools.getKeebDao(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        wireUp();
        populateCart();
    }

    private void wireUp(){
        CartBanner = findViewById(R.id.Cart_label);
        cartContents= findViewById(R.id.Cart_list);
        updateNum = findViewById(R.id.Cart_update_item_Number);
        newQty = findViewById(R.id.Cart_update_qty_input);
        ab = getSupportActionBar();
        ab.setTitle("Cart: "+user.getMUserName() );
        ab.setDisplayHomeAsUpEnabled(true);
        updateQty= findViewById(R.id.Cart_Update_qty_button);
        updateQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateQty()) populateCart();
                updateNum.setText("");
                newQty.setText("");
            }
        });

    }

    @SuppressLint("DefaultLocale")
    private void populateCart(){
        cartItems = kd.getCartItemsByUserName(user.getMUserName());
        total = 0.0;
        int counter =1;
        double price, qty;
        StringBuilder sb = new StringBuilder();
        for (CartItem item : cartItems){
            price = item.getCPrice();
            qty = item.getCQty();
            sb.append( counter++ + ") " +
                    item.getCDisplayItemname() + "\n"
                    + String.format("%-4.2f", price)
                    + " x " + String.format("%-4.2f", qty)
                    + " = " + String.format("%5.2f",price * qty) + "\n\n"
            );
           total += (price*qty);
        }

        sb.append( "=====================\n"+
                "Total: " + String.format("%20.2f", total));
        cartContents.setText(sb);
    }
    private Boolean updateQty(){
        int itemNum = -1;
        int itemQty;
        CartItem updateItem;
        try{
             itemNum = Integer.parseInt(updateNum.getText().toString());
             itemQty = Integer.parseInt(newQty.getText().toString());
             itemNum--;
        } catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid selection/input", Toast.LENGTH_SHORT).show();
            return false;
        }
        try {
             updateItem = cartItems.get(itemNum);
        } catch (IndexOutOfBoundsException e) {
            Toast.makeText(getApplicationContext(), "Invalid Item Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (itemQty<=0) {
            kd.delete(updateItem);
            Toast.makeText(getApplicationContext(), updateItem.getCDisplayItemname() + " removed from cart", Toast.LENGTH_SHORT).show();
        }
        else {
            updateItem.setCQty(itemQty);
            kd.update(updateItem);
            Toast.makeText(getApplicationContext(), updateItem.getCDisplayItemname() + ": update quantity", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    public static Intent getIntent(Context c){
        return new Intent(c, Cart.class);
    }
}