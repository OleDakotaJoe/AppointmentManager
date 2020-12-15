package me.stevensheaves.view.controllers.contacts;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import me.stevensheaves.custom.controls.TextFieldLimited;
import me.stevensheaves.data.model.Contact;
import me.stevensheaves.view.controllers.state.ContactDataState;
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
        if(!isFormComplete()) return;
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
    private void initializeForm() {
        ContactDataState.FormType formType = ContactDataState.getCurrentFormType();
        switch (formType) {
            case ADD:
                clearForm();
                setDisabledFields(false);
                break;
            case EDIT:
                populateForm();
                setDisabledFields(false);
                break;
            case VIEW:
                populateForm();
                setDisabledFields(true);
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
    private void populateForm() {
        Contact currentContact = ContactDataState.getSelectedContact();
        contactId.setText(String.valueOf(currentContact.getId()));
        contactName.setText(currentContact.getName());
        email.setText(currentContact.getEmail());
    }

    /**
     * Sets the <code>.setDisable()</code> property of controls in the view.
     * A value of false sets all appropriate forms to be enabled, and a value of true sets all forms to be disabled.
     * @param bool
     * Value to be set for the disable property.
     */
    private void setDisabledFields(boolean bool) {
        contactName.setDisable(bool);
        email.setDisable(bool);
        saveButton.setDisable(bool);
    }



    /**
     * Clears all text data from the appropriate <code>TextField</code>s.
     */
    private void clearForm() {
        contactId.setText("Auto-Generated...");
        contactName.setText("");
        email.setText("");
    }

    /**
     * Checks for completeness of the form
     * @return
     * Returns true if form is complete, and false if not.
     */
    private boolean isFormComplete() {
        boolean isComplete;
        if(contactName.getText().isBlank() || email.getText().isBlank()) {
            formNotCompleteAlert();
            isComplete = false;
        } else {
            isComplete = true;
        }
        return isComplete;
    }

    /**
     * Utility function for alerting the user that the form is not complete.a
     */
    private void formNotCompleteAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Form not Complete");
        alert.setHeaderText("All fields are required.");
        alert.setContentText("You have not completed the form. You're content has not been saved. Please complete all required fields then try again. ");
        alert.show();
    }
}
