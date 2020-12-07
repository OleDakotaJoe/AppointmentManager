package me.stevensheaves.data.model;

public class User {
    int id;
    String userName;

    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
