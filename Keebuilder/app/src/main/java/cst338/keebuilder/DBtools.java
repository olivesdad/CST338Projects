package cst338.keebuilder;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import cst338.keebuilder.db.AppDatabase;
import cst338.keebuilder.db.KeebDao;

public class DBtools {
    public static final String USER_ID = "userId";
    public static KeebDao getKeebDao(final Context context){
        //get Dao
        KeebDao temp = Room.databaseBuilder(context, AppDatabase.class, "KeebDB")
                .allowMainThreadQueries()
                .build()
                .getKeebDao();
        //check for users exist if not add default
        List<User> users = temp.getAllUsers();
        if (users.size() <=0){
            User defaultAndy = new User("andy", "password", true);
            temp.insert(defaultAndy);
        }
        return temp;
    }
}
