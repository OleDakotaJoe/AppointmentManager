package me.stevensheaves.view.controllers.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.User;

public class UsersList {
    // TODO: 12/19/2020 remove if unnecessary 
    private static final ObservableList<User> users = FXCollections.observableArrayList();


    public ObservableList<User> getUsers() {
        return users;
    }

    public void setUsers(ObservableList<User> users) {
        UsersList.users.setAll(users);
    }
}
