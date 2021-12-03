package cst338.keebuilder.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

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
}
