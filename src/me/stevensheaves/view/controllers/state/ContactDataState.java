package me.stevensheaves.view.controllers.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Contact;

/**
 * This class is intended for holding state data as related to the contact.fxml and contactform.fxml views.
 */
public class ContactDataState {
    private static FormType currentFormType;
    private static final ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    private static Contact selectedContact;

    /**
     * Getter for the currentFormType
     * @return returns the value held in the field: currentFormType.
     */
    public static FormType getCurrentFormType() {
        return currentFormType;
    }
    /**
     * Setter for the CurrentFormType field.
     * @param currentFormType The FormType to be set as currentFormType
     */
    public static void setCurrentFormType(FormType currentFormType) {
        ContactDataState.currentFormType = currentFormType;
    }
    /**
     * Getter for the list of allContacts.
     * @return Returns an ObservableList of allContacts.
     */
    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }
    /**
     * Sets the all of the allContacts field.
     * @param allContacts The list of Contacts to be set.
     */
    public static void setAllContacts(ObservableList<Contact> allContacts) {
        ContactDataState.allContacts.setAll(allContacts);
    }
    /**
     * Getter for the selectedContact field.
     * @return Returns the Contact set as the selectedContact
     */
    public static Contact getSelectedContact() {
        return selectedContact;
    }
    /**
     * Setter for the selectedContact field.
     * @param selectedContact the Contact to be set as the selectedContact.
     */
    public static void setSelectedContact(Contact selectedContact) {
        ContactDataState.selectedContact = selectedContact;
    }
    /**
     * This <code>enum</code> is for distinguishing between what kind of form should be loaded.
     */
    public enum FormType {
        ADD,
        EDIT,
        VIEW
    }
}
