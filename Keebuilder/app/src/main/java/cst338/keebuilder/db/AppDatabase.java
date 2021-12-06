package cst338.keebuilder.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import cst338.keebuilder.StoreItem;
import cst338.keebuilder.User;

@Database(entities = {User.class, StoreItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String USER_TABLE = "user_table";
    public static final String STORE_ITEMS = "store_items";

    public abstract KeebDao getKeebDao();
}
