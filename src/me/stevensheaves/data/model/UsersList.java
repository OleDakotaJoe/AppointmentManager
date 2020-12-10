package me.stevensheaves.data.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsersList {
    private  ObservableList<User> users = FXCollections.observableArrayList();

    public UsersList(ObservableList<User> users) {
        this.users = users;
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public void setUsers(ObservableList<User> users) {
        this.users = users;
    }
}
