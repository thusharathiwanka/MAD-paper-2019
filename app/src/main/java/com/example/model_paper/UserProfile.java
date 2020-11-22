package com.example.model_paper;

import android.provider.BaseColumns;

public final class UserProfile {
    private UserProfile() {}

    static class Users implements BaseColumns {
        public static final String  userTable = "users";
        public static final String username = "username";
        public static final String password = "password";
        public static final String dateOfBirth = "date_of_birth";
        public static final String gender = "gender";
    }
}
