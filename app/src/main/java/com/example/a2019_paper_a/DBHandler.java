package com.example.a2019_paper_a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String dbname = "moviedb";
    ArrayList<String> movies;
    ArrayList<String> years;
    ArrayList<String> comments;

    public DBHandler(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DatabaseMaster.Users.userTable + " ( user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseMaster.Users.username + " TEXT, " + DatabaseMaster.Users.password + " TEXT, " + DatabaseMaster.Users.userType + " TEXT)");
        db.execSQL("CREATE TABLE " + DatabaseMaster.Movie.movieTable + " ( movie_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseMaster.Movie.movieName + " TEXT, " + DatabaseMaster.Movie.movieYear + " INTEGER)");
        db.execSQL("CREATE TABLE " + DatabaseMaster.Comments.commentTable + " ( comment_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseMaster.Comments.movieName + " TEXT, " + DatabaseMaster.Comments.movieRating + " INTEGER, " +
                DatabaseMaster.Comments.movie_comments + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public long registerUser (String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseMaster.Users.username, username);
        contentValues.put(DatabaseMaster.Users.password, password);

        return sqLiteDatabase.insert(DatabaseMaster.Users.userTable, null, contentValues);
    }

    public String loginUser(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor usernameCheck = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseMaster.Users.userTable + " WHERE username = ?", new String[] {username});
        Cursor passwordCheck = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseMaster.Users.userTable + " WHERE password = ?", new String[] {password});

        if(usernameCheck.getCount() == 1 || passwordCheck.getCount() == 1) {
            Cursor detailsCheck = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseMaster.Users.userTable + " WHERE username = ? AND password = ?",
                    new String[] {username, password});

            if(detailsCheck.getCount() == 1) {
                return "exists";
            } else {
                return "not_matches";
            }
        } else {
            return "not_exists";
        }
    }

    public boolean addMovies(String movieName, int movieYear) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseMaster.Movie.movieName, movieName);
        contentValues.put(DatabaseMaster.Movie.movieYear, movieYear);

        long isInserted = sqLiteDatabase.insert(DatabaseMaster.Movie.movieTable, null, contentValues);

        return isInserted != -1;
    }

//    public Cursor viewMovies() {
//
//    }
//
//    public boolean insertComments() {
//
//    }
//
//    public Cursor viewComments() {
//
//    }

    public ArrayList<String> getMovies() {
        return movies;
    }

    public ArrayList<String> getYears() {
        return years;
    }

    public ArrayList<String> getComments() {
        return comments;
    }
}
