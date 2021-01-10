package me.stevensheaves.view.controllers.contacts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import me.stevensheaves.data.model.Contact;
import me.stevensheaves.view.controllers.state.ContactDataState;
import me.stevensheaves.database.utils.ContactDAO;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;

// TODO: 12/10/2020 Implement search functionality for contacts list.

/**
 * Controller for the <code>contacts.fxml</code> view.
 * Data that needs to be passed between this class and the <code>ContactsFormController</code> class
 * is passed using the <code>ContactDataState</code> controller.
 */
public class ContactsController {
    @FXML private Button appointmentsButton;
    @FXML private Button contactsButton;
    @FXML private Button reportsButton;
    @FXML private Button customersButton;
    @FXML private Button dashboardButton;
    @FXML private Button addContactButton, editContactButton, viewContactButton, deleteContactButton;
    @FXML private BorderPane parentPane;
    // TODO: 12/11/2020 test if i can specify type in the below table and remove casting in appropriate table data sections
    @FXML private TableView<Contact> contactsTable;
    @FXML private TableColumn<Contact,Integer> contactId;
    @FXML private TableColumn<Contact,String> contactName;
    @FXML private TableColumn<Contact,String> email;

    /**
     * Called when the class is instantiated.
     */
    @FXML
    private void initialize() {
        setTableData();

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
     * Sets the values for each column in the <code>contactsTable</code>.
     */
    @FXML
    private void setTableData() {
        fetchTableData();
        contactId.setCellValueFactory(new PropertyValueFactory<>("id"));
        contactName.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        contactsTable.setItems(ContactDataState.getAllContacts());
    }


    /**
     * Utility function for handling the changing of the scene.
     * This function works in tandem with the SceneChanger class to organize the navigation of the application.
     * it checks to see which button was clicked using the <code>ActionEvent</code>'s source, and changes the appropriate scene.
     * The purpose of this is to abstract the responsibility of scene changes away from this class, and handle it in one class, with static methods.
     * @param event
     * Passed by the button which calls the method.
     * @throws IOException
     * Throws and exception if there is an issue in the <code>SceneChanger.changeScene()</code> method call.
     */
    @FXML
    private void changeScene(ActionEvent event)  throws IOException {
        if(event.getSource().equals(appointmentsButton)) {
            SceneChanger.changeScene(SceneNames.APPOINTMENT);
        }
        if(event.getSource().equals(contactsButton)) {
            SceneChanger.changeScene(SceneNames.CONTACTS);
        }
        if(event.getSource().equals(customersButton)) {
            SceneChanger.changeScene(SceneNames.CUSTOMERS);
        }
        if(event.getSource().equals(reportsButton)) {
            SceneChanger.changeScene(SceneNames.REPORTS);
        }
        if(event.getSource().equals(dashboardButton)) {
            SceneChanger.changeScene(SceneNames.DASHBOARD);
        }
        if(event.getSource().equals(addContactButton)) {
            ContactDataState.setCurrentFormType(ContactDataState.FormType.ADD);
            SceneChanger.addChildScene(SceneNames.CONTACTS_FORM, parentPane);
        }
        if(event.getSource().equals(editContactButton)) {
            if(!isValidSelection()) return;

            ContactDataState.setCurrentFormType(ContactDataState.FormType.EDIT);
            ContactDataState.setSelectedContact(contactsTable.getSelectionModel().getSelectedItem());
            SceneChanger.addChildScene(SceneNames.CONTACTS_FORM, parentPane);
        }
        if(event.getSource().equals(viewContactButton)) {
            if(!isValidSelection()) return;

            ContactDataState.setCurrentFormType(ContactDataState.FormType.VIEW);
            ContactDataState.setSelectedContact(contactsTable.getSelectionModel().getSelectedItem());
            SceneChanger.addChildScene(SceneNames.CONTACTS_FORM, parentPane);
        }

    }

    /**
     * Deletes the selected Contact after displaying a confirmation dialog.
     */
    @FXML
    private void deleteContact() {
        if(!isValidSelection()) return;

        Contact selectedItem = (Contact) contactsTable.getSelectionModel().getSelectedItem();
        ButtonType result = showDeleteConfirmation(selectedItem.getName());
        if (result.equals(ButtonType.CANCEL)) return;
        ContactDAO dao = new ContactDAO();
        int selectedId = selectedItem.getId();
        dao.delete(selectedId);
        fetchTableData();
    }

    /**
     * Utility function for displaying a "Help" dialog box when the Help button is clicked in the view.
     */
    @FXML
    private void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Need Some help?");
        alert.setHeaderText("We've got your back. ");
        alert.setContentText("\n\n\t\t\t\tTo Add An Entry\n Click the \"Add\" button. Make sure all fields have been filled out. Note: you cannot choose the ID, it will be" +
                "automatically generated." +
                "\n\n\t\t\t\tTo Edit an Entry\nFirst click on the entry you would like to edit, then click \"Edit\". Afterwards, " +
                "you may edit any field which needs to be updated. When you are finished, click \"Save\". If you would like to discard changes, you may click \"cancel\" " +
                "\n\n\t\t\t\tTo View an Entry\nFirst click on the entry you would like to view, then click \"View\". If you click " +
                "\"View\" all fields will be disabled. If you would like to discard changes, you may click \"cancel\" " +
                "\n\n\t\t\t\tTo Delete an Entry\nFirst click on the entry you would like to delete, then click \"Delete\". You " +
                "will see a pop-up to confirm that you would like to delete the entry. Only click \"OK\" if you are certain that you would like to delete the entry.");
        alert.show();
    }

    /**
     * Checks whether the user has selected a contact from the table
     * @return
     * Returns true if a contact is selected, false otherwise.
     * Returns false and shows a alert if no contact is selected, or if table is empty.
     */
    private boolean isValidSelection() {
        if(ContactDataState.getAllContacts().isEmpty()) {
            showEmptyListAlert();
            return false;
        }
        int index = contactsTable.getSelectionModel().getSelectedIndex();
        if(index < 0) {
            showNoContactSelectedAlert();
            return false;
        }
        return true;
    }

    /**
     * Shows an Alert dialog that informs the user that the list of contacts is empty.
     */
    @FXML
    private void showEmptyListAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No contacts");
        alert.setHeaderText("You haven't added any contacts yet.");
        alert.setContentText("Before you can complete this action, you must first add a contact to the database. Try adding " +
                "a contact before proceeding.");
        alert.show();
    }

    /**
     * Shows an Alert dialog that confirms that the user wants to delete the selected contact.
     * @param contactName
     * When calling this function, the String value of the contact to be deleted is passed in, to inform the user which contact is being deleted.
     * @return
     * Returns the button type that the user clicks, so that the method which calls it may determine whether or not to proceed.
     */
    @FXML
    private ButtonType showDeleteConfirmation(String contactName) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact");
        alert.setHeaderText("Are you sure you want to delete " + contactName + "?");
        alert.setContentText("This action cannot be reversed. Only proceed if you are absolutely sure you want to delete this contact.");
        alert.showAndWait();
        return alert.getResult();
    }
    /**
     * Shows an Alert dialog that informs the user that no contact is selected.
     */
    @FXML
    private void showNoContactSelectedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No contact selected");
        alert.setHeaderText("You didn't select a contact");
        alert.setContentText("Before you can complete this action, you must first select a contact. to the database. Try clicking on " +
                "a contact before proceeding.");
        alert.show();
    }

}
