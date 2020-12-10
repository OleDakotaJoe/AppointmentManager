package me.stevensheaves.view.controllers.contacts;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import me.stevensheaves.custom.controls.TextFieldLimited;
import me.stevensheaves.data.model.Contact;
import me.stevensheaves.database.utils.ContactDAO;

/**
 * Controller for the <code>contactsform.fxml</code> view.
 * Data is passed between this class and the <code>ContactsController</code> class via the <code>ContactDataState</code> class.
 */
public class ContactsFormController {
    @FXML Button saveButton, cancelButton;
    @FXML TextField contactId;
    @FXML TextFieldLimited contactName, email;

    /**
     * Called when the class is instantiated.
     * Calls the <code>initializeForm()</code> method, which populates data or disables/enables appropriate fields conditionally.
     */
    @FXML
    private void initialize() {
        initializeForm();
    }

    /**
     * Utility function for making a call to the database, and setting the <code>allContacts</code> <code>ObservableList</code>'s data.
     * This method is used anytime a change is made to the database, which needs to be updated in the <code>contactsTable</code> element in the
     * <code>contacts.fxml</code> view.
     */
    private void fetchTableData() {
        ContactDAO dao = new ContactDAO();
        ContactDataState.setAllContacts(dao.findAll());
    }

    /**
     * Handles the <code>onAction</code> event when the <code>saveButton</code> is clicked.
     * behavior is different depending on which form is currently being displayed.
     * Creates or updates a given contact depending on whether the edit or add form is being displayed.
     */
    @FXML
    private void handleSaveContact() {
        ContactDAO dao = new ContactDAO();
        ContactDataState.FormType typeOfForm = ContactDataState.getCurrentFormType();
        Contact contact;
        boolean isSaved;
        switch (typeOfForm) {
            case ADD:
                contact = new Contact(contactName.getText(), email.getText());
                isSaved = dao.create(contact);
                if (isSaved) {
                    ContactDataState.setSelectedContact(dao.findLast());
                    ContactDataState.setCurrentFormType(ContactDataState.FormType.VIEW);
                    fetchTableData();
                    initializeForm();
                }
                break;
            case EDIT:
                contact = new Contact(Integer.parseInt(contactId.getText()), contactName.getText(), email.getText());
                isSaved = dao.update(contact);
                if (isSaved) {
                    ContactDataState.setCurrentFormType(ContactDataState.FormType.VIEW);
                    fetchTableData();
                    initializeForm();
                }
                break;
            default:
                return;
        }
    }

    /**
     * Method for initializing the Form data.
     * Switch method which calls specific methods appropriate to each FormType.
     * FormType is first checked, and then the appropriate methods are called depending on which type the current form is.
     */
    @FXML
    private void initializeForm() {
        ContactDataState.FormType formType = ContactDataState.getCurrentFormType();
        switch (formType) {
            case ADD:
                clearForm();
                enableFormFields();
                break;
            case EDIT:
                populateForm();
                enableFormFields();
                break;
            case VIEW:
                populateForm();
                disableAllFormFields();
                break;
            default:
                break;
        }
    }

    /**
     * Gets the data from the current selected contact, and populates the data into the form.
     * <code>ContactDataState</code> acts a data-shuttle containing pertinent data to the state of the application, including which contact is currently selected in the
     * <code>contactsTable</code> in the <code>contacts.fxml</code> view.
     */
    @FXML
    private void populateForm() {
        Contact currentContact = ContactDataState.getSelectedContact();
        contactId.setText(String.valueOf(currentContact.getId()));
        contactName.setText(currentContact.getName());
        email.setText(currentContact.getEmail());
    }

    /**
     * Disables all form fields, as well as the <code>saveButton</code>.
     */
    @FXML
    private void disableAllFormFields() {
        contactName.setDisable(true);
        email.setDisable(true);
        saveButton.setDisable(true);
    }
    /**
     * Enables all form fields, as well as the <code>saveButton</code>.
     */
    @FXML
    private void enableFormFields() {
        contactName.setDisable(false);
        email.setDisable(false);
        saveButton.setDisable(false);
    }

    /**
     * Clears all text data from the appropriate <code>TextField</code>s.
     */
    @FXML
    private void clearForm() {
        contactId.setText("Auto-Generated...");
        contactName.setText("");
        email.setText("");
    }

}
