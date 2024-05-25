package com.example.knk_gr23.App;

import com.example.knk_gr23.Models.Client;
import com.example.knk_gr23.Models.User;

public class SessionMenager
{
    private static User user;

    private static Client client;

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        SessionMenager.client = client;
    }
    public static void clearClient() {
        SessionMenager.client = null;
    }

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
