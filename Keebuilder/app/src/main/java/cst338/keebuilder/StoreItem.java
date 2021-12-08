package cst338.keebuilder;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import cst338.keebuilder.db.AppDatabase;

@Entity(tableName = AppDatabase.STORE_ITEMS)
public class StoreItem {

    @PrimaryKey(autoGenerate = true)
    private int mItemNumber;

    private String mItemName;
    private String mDescription;
    private String mCategory;
    private String mDisplayName;
    private int mQty;
    private double mPrice;

    public StoreItem(String mItemName, String mDisplayName, String mDescription, String mCategory, int mQty, double mPrice) {
        this.mDescription = mDescription;
        this.mDisplayName = mDisplayName;
        this.mItemName = mItemName;
        this.mCategory = mCategory;
        this.mQty = mQty;
        this.mPrice = mPrice;
    }

    public String getMDisplayName() {
        return mDisplayName;
    }

    public void setMDisplayName(String mDisplayName) {
        this.mDisplayName = mDisplayName;
    }

    public int getMItemNumber() {
        return mItemNumber;
    }

    public void setMItemNumber(int mItemNumber) {
        this.mItemNumber = mItemNumber;
    }

    public String getMItemName() {
        return mItemName;
    }

    public void setMItemName(String mItemName) {
        this.mItemName = mItemName;
    }

    public String getMDescription() {
        return mDescription;
    }

    public void setMDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getMCategory() {
        return mCategory;
    }

    public void setMCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public int getMQty() {
        return mQty;
    }

    public void setMQty(int mQty) {
        this.mQty = mQty;
    }

    public double getMPrice() {
        return mPrice;
    }

    public void setMPrice(double mPrice) {
        this.mPrice = mPrice;
    }
}
