package me.stevensheaves.view.controllers.customers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import me.stevensheaves.data.model.Customer;
import me.stevensheaves.data.utils.Validator;
import me.stevensheaves.database.utils.AppointmentDAO;
import me.stevensheaves.database.utils.CustomerDAO;
import me.stevensheaves.view.controllers.state.CustomerDataState;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;


// TODO: 12/12/2020 search functionality

/**
 * Controller class for the <code>customers.fxml</code> view.
 */
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
    @FXML private TableColumn<Customer, String> divisionName;


    /**
     * Retrieves and sets table data during the initialization of the instance of this class.
     */
    @FXML
    private void initialize() {
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
        divisionName.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        customersTable.setItems(CustomerDataState.getAllCustomers());
    }

    /**
     * Utility function for changing the scenes.
     * @param event
     * The event which calls the funcion.
     * Used for checking which button was clicked, and which scene to show.
     * @throws IOException
     * Throws and IOException if there are any errors when loading the FMXL file
     */
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
            if(!isValidSelection()) return;
            CustomerDataState.setCurrentFormType(CustomerDataState.FormType.EDIT);
            CustomerDataState.setSelectedCustomer(customersTable.getSelectionModel().getSelectedItem());
            SceneChanger.addChildScene(SceneNames.CUSTOMER_FORM, mainPane);
        }
        if(event.getSource().equals(viewCustomerButton)) {
            if(!isValidSelection()) return;
            CustomerDataState.setCurrentFormType(CustomerDataState.FormType.VIEW);
            CustomerDataState.setSelectedCustomer(customersTable.getSelectionModel().getSelectedItem());
            SceneChanger.addChildScene(SceneNames.CUSTOMER_FORM, mainPane);
        }
    }

    /**
     * Deletes the selected customer.
     */
    @FXML
    private void deleteCustomer() {
        if(!isValidSelection()) return;
        Customer selected = customersTable.getSelectionModel().getSelectedItem();
        if((new AppointmentDAO().findByCustomerId(selected.getCustomerId())).size() > 0 ) {
            showCustomerHasAppointmentsAlert(selected.getCustomerId());
            return;
        }
        ButtonType buttonType = showDeleteConfirmation(selected.getCustomerName());
        if(buttonType.equals(ButtonType.CANCEL)) return;

        CustomerDAO dao = new CustomerDAO();
        dao.delete(selected.getCustomerId());
        fetchTableData();
    }

    /**
     * Checks whether the user has selected a customer from the table
     * @return
     * Returns true if a customer is selected, false otherwise.
     * Returns false and shows a alert if no customer is selected, or if table is empty.
     */
    private boolean isValidSelection() {
        if(CustomerDataState.getAllCustomers().isEmpty()) {
            showEmptyListAlert();
            return false;
        }

        int index = customersTable.getSelectionModel().getSelectedIndex();
        if(index < 0) {
            showNoCustomerSelectedAlert();
            return false;
        }
        return true;
    }

    /**
     * Shows an Alert dialog that informs the user that the list of customers is empty.
     */
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
    private void showNoCustomerSelectedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No customer selected");
        alert.setHeaderText("You didn't select a customer");
        alert.setContentText("Before you can complete this action, you must first select a customer. Try clicking on " +
                "a customer before proceeding.");
        alert.show();
    }

    private void showCustomerHasAppointmentsAlert( int id) {
        // TODO: 12/19/2020 create Appointment sorted by user/cx/contact  report
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Customer cannot be deleted");
        alert.setHeaderText("This customer still has appointments scheduled.");
        alert.setContentText("Before you can complete this action, you must first delete all appointments associated with this customer. You can visit the reports tab to" +
                " view an \"All appointments by Customer ID\" report to find out which report is associated with this customer. \n\n This customer's ID is: " + id);
        alert.show();
    }

}
