package cst338.keebuilder.db;
/*
 * ______ __         ______ ______________________
 * ___  //_/____________  /____  __ \__    |_  __ \
 * __  ,<  _  _ \  _ \_  __ \_  / / /_  /| |  / / /
 * _  /| | /  __/  __/  /_/ /  /_/ /_  ___ / /_/ /
 * /_/ |_| \___/\___//_.___//_____/ /_/  |_\____/
 * Andrew Shiraki
 * 2021-12-5
 * This is the KeebDao class it is the DAO for our app database. It defines standard database management methods and Queries
 */
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cst338.keebuilder.StoreItem;
import cst338.keebuilder.User;

@Dao
public interface KeebDao {

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + "  WHERE mUserName = :name")
    User getUserByName(String name);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + "  WHERE mUserId = :id")
    User getUserById(int id);

    //<------------------DB stuff for storeItems---------------->
    @Insert
    void insert(StoreItem... storeItems);

    @Update
    void update(StoreItem... storeItems);

    @Delete
    void delete(StoreItem storeItem);

    @Query("SELECT * FROM " + AppDatabase.STORE_ITEMS)
    List<StoreItem> getAllStoreItems();

    @Query("SELECT * FROM " + AppDatabase.STORE_ITEMS + "  WHERE mItemName = :name")
    StoreItem getStoreItemByName(String name);

    @Query("SELECT * FROM " + AppDatabase.STORE_ITEMS + "  WHERE mItemNumber = :number" +" ORDER by mPrice")
    StoreItem getStoreItemByNumber(int number);
}
