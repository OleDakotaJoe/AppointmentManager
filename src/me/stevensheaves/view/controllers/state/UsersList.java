package me.stevensheaves.view.controllers.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.User;

public class UsersList {
    private static final ObservableList<User> users = FXCollections.observableArrayList();


    public ObservableList<User> getUsers() {
        return users;
    }

    public void setUsers(ObservableList<User> users) {
        UsersList.users.setAll(users);
    }
}
