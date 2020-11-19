package com.example.a2019_paper_a;

import android.provider.BaseColumns;

public class DatabaseMaster {
    public DatabaseMaster() {}

    static class Users implements BaseColumns {
        public static final String userTable = "users";
        public static final String username = "username";
        public static final String password = "password";
        public static final String userType = "user_type";
    }

    static class Movie implements BaseColumns {
        public static final String movieTable = "movies";
        public static final String movieName = "movie_name";
        public static final String movieYear = "movie_year";
    }

    static class Comments implements BaseColumns {
        public static final String commentTable = "comments";
        public static final String movieName = "movie_name";
        public static final String movieRating = "movie_rating";
        public static final String movie_comments = "movie_comments";
    }
}
