package cst338.keebuilder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.room.Room;

import java.util.List;

import cst338.keebuilder.db.AppDatabase;
import cst338.keebuilder.db.KeebDao;

public class DBtools {
    public static final String USER_ID = "userId";
    public static final String SP = "keeb_builder_preferences";
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
    //use this to return the activeuser based on the userId in shared pref insance
    public static User getActiveUser(Context context){
        KeebDao kd = getKeebDao(context);
        SharedPreferences sharedPref = context.getSharedPreferences(DBtools.SP, Context.MODE_PRIVATE);
        int userId = sharedPref.getInt(DBtools.USER_ID, -1);
        if(userId != -1){
            Intent intent = TopLevelMenu.getTopLevelMenuIntent(context.getApplicationContext(), userId);
            return kd.getUserById(userId);
        }else{
            return null;
        }
    }
    public static boolean deleteUserByUserName(String name, KeebDao kd){
        User user = null;
        user = kd.getUserByName(name);
        if (user == null) return false;
        else if (user.getMUserId() == 1) return false;
        else{
            kd.delete(user);
            return true;
        }
    }
    //take a string name and KeebDao. Tries to 
    public static boolean changeAdminStatus(String name, KeebDao kd){
        User user = kd.getUserByName(name);
        if (user == null) return false;
        else if (user.isMIsAdmin()){
            user.setMIsAdmin(false);
            kd.update(user);
            return false;
        }else{
            user.setMIsAdmin(true);
            kd.update(user);
            return true;
        }
    }
}
