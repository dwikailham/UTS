package com.dwikailham.beritaharian.models;

@SuppressWarnings("ALL")
public class User {
    public String id;
    public String username;
    public String password;
    public String nama;

    public User(String id, String username, String password, String nama) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() { return nama; }

    public void setNama(String nama) { this.nama = nama; }
}
