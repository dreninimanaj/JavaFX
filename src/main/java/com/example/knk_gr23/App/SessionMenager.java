package com.example.knk_gr23.App;

import com.example.knk_gr23.Models.User;

public class SessionMenager
{
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        SessionMenager.user = user;
    }

    public static void clearUser() {
        SessionMenager.user = null;
    }
}
