package me.stevensheaves.view.controllers.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Contact;

public class ContactDataState {
    private static FormType currentFormType;
    private static final ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    // TODO: 12/19/2020 remove if possible 
    //private static final ObservableList<String> contactNames = FXCollections.observableArrayList();
    private static Contact selectedContact;

    public static FormType getCurrentFormType() {
        return currentFormType;
    }

    public static void setCurrentFormType(FormType currentFormType) {
        ContactDataState.currentFormType = currentFormType;
    }

    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }
// TODO: 12/19/2020 remove if possible 
/*    public static ObservableList<String> getContactNames() {
        return contactNames;
    }*/

    public static void setAllContacts(ObservableList<Contact> allContacts) {
        ContactDataState.allContacts.setAll(allContacts);
        // TODO: 12/19/2020 remove if possible
        /* for (Contact contact : getAllContacts()) {
            contactNames.add(contact.getName());
        }*/
    }

    public static Contact getSelectedContact() {
        return selectedContact;
    }

    public static void setSelectedContact(Contact selectedContact) {
        ContactDataState.selectedContact = selectedContact;
    }

    public enum FormType {
        ADD,
        EDIT,
        VIEW
    }
}
