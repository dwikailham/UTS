package com.dwikailham.beritaharian.models;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static List<User> users;
    private static List<Posting> postings;

    public static List<User> getUsers() {
        return users;
    }

    @SuppressWarnings("unused")
    public static List<Posting> getPostings() {
        return postings;
    }

    @SuppressWarnings("unused")
    public static void setPostings(List<Posting> postings) {
        Data.postings = postings;
    }

    @SuppressWarnings("unused")
    public static void setUsers(List<User> users) {
        Data.users = users;
    }

    static {
        users = new ArrayList<>();
        users.add(new User("1","adi","rahasia","adi"));
        users.add(new User("2","beni","rahasia", "beni"));
        users.add(new User("3","cindy","rahasia","cindy"));
        users.add(new User("4","a","1","test"));

        postings = new ArrayList<>();
        postings.add(new Posting("1","ini judul 1", "ini perusahaan","ini persyaratan","10-062019","ini gambar"));
    }
}
