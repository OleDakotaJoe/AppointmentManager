package me.stevensheaves.view.controllers.customers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import me.stevensheaves.data.model.Customer;
import me.stevensheaves.database.utils.ContactDAO;
import me.stevensheaves.database.utils.CustomerDAO;
import me.stevensheaves.view.controllers.state.ContactDataState;
import me.stevensheaves.view.controllers.state.CustomerDataState;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;

public class CustomersController {
    @FXML private Button appointmentsButton;
    @FXML private Button contactsButton;
    @FXML private Button reportsButton;
    @FXML private Button customersButton;
    @FXML private Button dashboardButton;
    @FXML private Button addCustomerButton, editCustomerButton, viewCustomerButton, deleteCustomerButton;
    @FXML private BorderPane mainPane;
    @FXML private TableView<Customer> customersTable;
    @FXML private TableColumn<Customer, Integer> customerId;
    @FXML private TableColumn<Customer, String> customerName;
    @FXML private TableColumn<Customer, String> customerAddress;
    @FXML private TableColumn<Customer, String> postalCode;
    @FXML private TableColumn<Customer, String> phoneNumber;
    @FXML private TableColumn<Customer, Integer> divisionId;

    
    @FXML
    private void initialize() {
        fetchTableData();
        setTableData();
    }


    /**
     * Utility function for making a call to the database, and setting the <code>allCustomers</code> <code>ObservableList</code>'s data.
     * This method is used anytime a change is made to the database, which needs to be updated in the <code>customersTable</code> element in the
     * <code>customers.fxml</code> view.
     */
    private void fetchTableData() {
        CustomerDAO dao = new CustomerDAO();
        CustomerDataState.setAllCustomers(dao.findAll());
    }

    /**
     * Sets the values for each column in the <code>customersTable</code>.
     */
    @FXML
    private void setTableData() {
        fetchTableData();
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        divisionId.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        customersTable.setItems(CustomerDataState.getAllCustomers());
    }
    @FXML
    private void changeScene(ActionEvent event) throws IOException {
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
        if(event.getSource().equals(addCustomerButton)) {
            CustomerDataState.setCurrentFormType(CustomerDataState.FormType.ADD);
            SceneChanger.addChildScene(SceneNames.CUSTOMER_FORM, mainPane);
        }
        if(event.getSource().equals(editCustomerButton)) {
            if(CustomerDataState.getAllCustomers().isEmpty()) {
                showEmptyListAlert();
                return;
            }

            int index = customersTable.getSelectionModel().getSelectedIndex();
            if(index < 0) {
                showNoCustomerSelectedAlert();
                return;
            }
            CustomerDataState.setCurrentFormType(CustomerDataState.FormType.EDIT);
            CustomerDataState.setSelectedCustomer((Customer) customersTable.getSelectionModel().getSelectedItem());
            SceneChanger.addChildScene(SceneNames.CUSTOMER_FORM, mainPane);
        }
        if(event.getSource().equals(viewCustomerButton)) {
            if(CustomerDataState.getAllCustomers().isEmpty()) {
                showEmptyListAlert();
                return;
            }

            int index = customersTable.getSelectionModel().getSelectedIndex();
            if(index < 0) {
                showNoCustomerSelectedAlert();
                return;
            }
            CustomerDataState.setCurrentFormType(CustomerDataState.FormType.VIEW);
            CustomerDataState.setSelectedCustomer((Customer) customersTable.getSelectionModel().getSelectedItem());
            SceneChanger.addChildScene(SceneNames.CUSTOMER_FORM, mainPane);
        }
    }

    @FXML
    private void deleteCustomer() {
        // TODO: 12/11/2020 implement delete customer function
    }

    /**
     * Shows an Alert dialog that informs the user that the list of customers is empty.
     */
    @FXML
    private void showEmptyListAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No customers");
        alert.setHeaderText("You haven't added any customers yet.");
        alert.setContentText("Before you can complete this action, you must first add a customer to the database. Try adding " +
                "a customer before proceeding.");
        alert.show();
    }

    /**
     * Shows an Alert dialog that confirms that the user wants to delete the selected contact.
     * @param customerName
     * When calling this function, the String value of the contact to be deleted is passed in, to inform the user which contact is being deleted.
     * @return
     * Returns the button type that the user clicks, so that the method which calls it may determine whether or not to proceed.
     */
    @FXML
    private ButtonType showDeleteConfirmation(String customerName) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Customer");
        alert.setHeaderText("Are you sure you want to delete " + customerName + "?");
        alert.setContentText("This action cannot be reversed. Only proceed if you are absolutely sure you want to delete this customer.");
        alert.showAndWait();
        return alert.getResult();
    }
    /**
     * Shows an Alert dialog that informs the user that no contact is selected.
     */
    @FXML
    private void showNoCustomerSelectedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No customer selected");
        alert.setHeaderText("You didn't select a customer");
        alert.setContentText("Before you can complete this action, you must first select a customer. to the database. Try clicking on " +
                "a customer before proceeding.");
        alert.show();
    }

}
