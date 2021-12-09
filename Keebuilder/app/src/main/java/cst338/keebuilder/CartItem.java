package cst338.keebuilder;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import cst338.keebuilder.db.AppDatabase;

@Entity(tableName = AppDatabase.CART_ITEMS)
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    private int cItemId;

    private String cItemName;

    private String cCategory;
    private String cUser;
    private int cQty;
    private double cPrice;

    //CONSTRUCTOR
    public CartItem(String cItemName, String cCategory, String cUser, int cQty, double cPrice){
        this.cItemName = cItemName;
        this.cCategory=cCategory;
        this.cUser = cUser;
        this.cQty = cQty;
        this.cPrice = cPrice;
    }

    public int getCItemId() {
        return cItemId;
    }

    public void setCItemId(int cItemId) {
        this.cItemId = cItemId;
    }

    public String getCItemName() {
        return cItemName;
    }

    public void setCItemName(String cItemName) {
        this.cItemName = cItemName;
    }

    public String getCCategory() {
        return cCategory;
    }

    public void setCCategory(String cCategory) {
        this.cCategory = cCategory;
    }

    public String getCUser() {
        return cUser;
    }

    public void setCUser(String cUser) {
        this.cUser = cUser;
    }

    public int getCQty() {
        return cQty;
    }

    public void setCQty(int cQty) {
        this.cQty = cQty;
    }

    public double getCPrice() {
        return cPrice;
    }

    public void setCPrice(double cPrice) {
        this.cPrice = cPrice;
    }
}
