package cst338.keebuilder;
/*
 ____ ____ ____ ____
||U |||S |||E |||R ||
||__|||__|||__|||__||
|/__\|/__\|/__\|/__\|
 * Andrew Shiraki
 * 2021-12-5
 * This is the User class. It is an Entity for our database.
 * Keys include username, password, and is admin
 */

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import cst338.keebuilder.db.AppDatabase;

@Entity (tableName = AppDatabase.USER_TABLE)
public class User {


    @PrimaryKey(autoGenerate = true)
    private int mUserId;
    private String mUserName, mUserPassword;
    boolean mIsAdmin;

    public User(String mUserName, String mUserPassword, boolean mIsAdmin) {
        this.mUserName = mUserName;
        this.mIsAdmin = mIsAdmin;
        this.mUserPassword = mUserPassword;
    }
    public int getMUserId() {
        return mUserId;
    }

    public boolean isMIsAdmin() {
        return mIsAdmin;
    }

    public void setMIsAdmin(boolean mIsAdmin) {
        this.mIsAdmin = mIsAdmin;
    }

    public void setMUserId(int mUserId) {
        this.mUserId = mUserId;
    }
    public String getMUserName() {
        return mUserName;
    }

    public void setMUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getMUserPassword() {
        return mUserPassword;
    }

    public void setMUserPassword(String mUserPassword) {
        this.mUserPassword = mUserPassword;
    }
}
