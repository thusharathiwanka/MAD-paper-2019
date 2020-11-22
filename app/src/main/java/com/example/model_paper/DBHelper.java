package com.example.model_paper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "UserDB";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + UserProfile.Users.userTable + " ( " + UserProfile.Users._ID + " INTEGER PRIMARY KEY, " +
                UserProfile.Users.username + " TEXT, " + UserProfile.Users.password + " TEXT, " + UserProfile.Users.dateOfBirth + " TEXT, " +
                UserProfile.Users.gender + " TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserProfile.Users.userTable);
    }

    public long addInfo(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserProfile.Users.username, username);
        contentValues.put(UserProfile.Users.password, password);

        return sqLiteDatabase.insert(UserProfile.Users.userTable, null, contentValues);
    }

    public boolean updateInfo(String userID, String username, String dob, String password, String gender) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserProfile.Users.username, username);
        contentValues.put(UserProfile.Users.dateOfBirth, dob);
        contentValues.put(UserProfile.Users.password, password);
        contentValues.put(UserProfile.Users.gender, gender);

        String selection = UserProfile.Users._ID + " LIKE ?";

        int isUpdated = sqLiteDatabase.update(UserProfile.Users.userTable, contentValues, selection, new String[] {userID});

        return isUpdated > 0;
    }

    public Cursor readAllInfo() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String[] columns = {
                UserProfile.Users.username,
                UserProfile.Users.password,
                UserProfile.Users.dateOfBirth,
                UserProfile.Users.gender
        };

        return sqLiteDatabase.query(UserProfile.Users.userTable, columns, null, null, null, null, null);
    }

    public Cursor readAllInfo(String username) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String selection = UserProfile.Users.username + " = ?";

        return sqLiteDatabase.query(UserProfile.Users.userTable, null, selection, new String[] {username}, null, null, null);
    }

    public boolean deleteInfo(String username) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String selection = UserProfile.Users.username + " LIKE ?";

        int isDeleted  = sqLiteDatabase.delete(UserProfile.Users.userTable, selection, new String[] {username});

        return isDeleted > 0;
    }

    public Cursor loginUser(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        return sqLiteDatabase.rawQuery("SELECT * FROM " + UserProfile.Users.userTable + " WHERE username = ? AND password = ? ",
                new String[] {username, password});
    }
}
