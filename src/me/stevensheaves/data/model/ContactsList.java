package me.stevensheaves.data.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactsList {

    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public ContactsList(ObservableList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ObservableList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ObservableList<Contact> contacts) {
        this.contacts = contacts;
    }
}
