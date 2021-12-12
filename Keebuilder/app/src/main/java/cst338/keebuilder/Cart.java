package cst338.keebuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class Cart extends AppCompatActivity {

    TextView CartBanner, cartContents;
    EditText updateNum, newQty;
    Button updateQty, backToStoreButton, buyItButton;
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
        buyItButton = findViewById(R.id.BUYIT);
        buyItButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete all the cart items
                dumpCart();
                //go to the fun page
                startActivity(Tyvm.getIntent(getApplicationContext()));
            }
        });
        backToStoreButton = findViewById(R.id.Cart_back_to_store);
        backToStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Shopping.getIntent(getApplicationContext()));
            }
        });
        CartBanner = findViewById(R.id.Cart_label);
        cartContents= findViewById(R.id.Cart_list);
        cartContents.setMovementMethod(new ScrollingMovementMethod());
        updateNum = findViewById(R.id.Cart_update_item_Number);
        newQty = findViewById(R.id.Cart_update_qty_input);
        ab = getSupportActionBar();
        ab.setTitle("Cart: "+user.getMUserName() );
        ab.setDisplayHomeAsUpEnabled(true);
        updateQty= findViewById(R.id.Cart_Update_qty_button);
        updateQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateQty()) populateCart(); //if the qty update happens we need to refresh the cart
                updateNum.setText(""); //clear values regardless
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
            sb.append( String.format("|%-40.40s|",counter++ + ") " + item.getCDisplayItemname()) + "\n"
                    +String.format("|%40.40s|", String.format("$%-4.2f", price)
                    + " x " + String.format("%-4.2f", qty)
                    + " = " + String.format("$%5.2f",price * qty) )+ "\n"
            );
           total += (price*qty);
        }

        sb.append( "|========================================|\n"+
                "|Total: " + String.format("%33.2f|", total));
        cartContents.setText(sb);
    }
    private Boolean updateQty(){
        int itemNum = -1;
        int itemQty;
        CartItem updateItem;
        try{ //try to get integers from input
             itemNum = Integer.parseInt(updateNum.getText().toString());
             itemQty = Integer.parseInt(newQty.getText().toString());
             itemNum--;
        } catch (NumberFormatException e){ //quit if the values are somethign other than ints (shouldnt be posible)
            Toast.makeText(getApplicationContext(), "Invalid selection/input", Toast.LENGTH_SHORT).show();
            return false;
        }
        try { //try to get item from cartItems table
             updateItem = cartItems.get(itemNum);
        } catch (IndexOutOfBoundsException e) { //need to catch if user put in some weird value
            Toast.makeText(getApplicationContext(), "Invalid Item Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (itemQty<=0) { //if the user put a value that is 0 or less we need to delete the item
            //delete item from cart db
            kd.delete(updateItem);
            //add stock back to store
            StoreItem temp = kd.getStoreItemByName(updateItem.getCItemName());
            if (temp == null ){ //prob shouldnt happen but if it does our logic is bad somewhere prob should erase database
                Toast.makeText(getApplicationContext(), "Something very bad has happened, please nuke this app", Toast.LENGTH_SHORT).show();
                return false;
            } else { // update the value of the store item by adding the qty
                temp.setMQty(temp.getMQty() + updateItem.getCQty());
                kd.update(temp); //push back to db
            }
            Toast.makeText(getApplicationContext(), updateItem.getCDisplayItemname() + " removed from cart", Toast.LENGTH_SHORT).show();
        }
        else { //value an adjustment
            int delta = updateItem.getCQty() - itemQty; //this is the adjustment made to the cart quantity negative means we need to deduct from the stock
            //must check we have stock
            StoreItem temp = kd.getStoreItemByName(updateItem.getCItemName()); //get the stock item
            if (temp.getMQty() + delta < 0 ){ //if subtracting the requested amount from stock creates a negative value, we do not have enough stock for request
                Toast.makeText(getApplicationContext(), "Not enough stock to fulfill request", Toast.LENGTH_SHORT).show();
                return false; //return do nothing
            }else {
                temp.setMQty(temp.getMQty() + delta); //if we can fulfill the request add the delta into stock
                updateItem.setCQty(itemQty); //set the desired qty of cart item
                kd.update(updateItem); //push back to db
                kd.update(temp); //push back to db
                Toast.makeText(getApplicationContext(), updateItem.getCDisplayItemname() + ": update quantity", Toast.LENGTH_SHORT).show();
            }
        }
        return true;//a change was made return true
    }
    private void dumpCart(){ //erase all items belonging to the user from the db
        cartItems = kd.getCartItemsByUserName(user.getMUserName());
        for (CartItem item : cartItems){
            kd.delete(item);
        }
    }
    public static Intent getIntent(Context c){
        return new Intent(c, Cart.class);
    }
}