package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import cst338.keebuilder.db.KeebDao;

public class Cart extends AppCompatActivity {

    TextView CartBanner, cartContents;
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
        ab = getSupportActionBar();
        ab.setTitle("Cart: "+user.getMUserName() );
        ab.setDisplayHomeAsUpEnabled(true);

    }

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
    public static Intent getIntent(Context c){
        return new Intent(c, Cart.class);
    }
}