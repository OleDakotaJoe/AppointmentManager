package me.stevensheaves.view.controllers.customers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import me.stevensheaves.custom.controls.TextFieldLimited;
import me.stevensheaves.data.model.*;
import me.stevensheaves.database.utils.CountryDAO;
import me.stevensheaves.database.utils.CustomerDAO;
import me.stevensheaves.database.utils.DivisionDAO;
import me.stevensheaves.database.utils.UserDAO;
import me.stevensheaves.view.controllers.state.CustomerDataState;

public class CustomersFormController {
    // TODO: 12/10/2020 add initialization for users.
    @FXML private ComboBox<String> country;
    @FXML private ComboBox<String> division;
    @FXML private ComboBox<String> createdByUserName;
    @FXML private GridPane mainPane;
    @FXML private TextFieldLimited name,phoneNumber,address, postalCode;
    @FXML private TextField customerId;
    @FXML private Button saveButton, cancelButton;


    @FXML
    private void initialize() {
        initializeLocationComboBoxes();
        initializeUserComboBox();
        initializeForm();
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

    private void initializeLocationComboBoxes() {
        CountryDAO countryDAO = new CountryDAO();
        CustomerDataState.setCountryList(countryDAO.findAll());
        country.setItems(CustomerDataState.getCountryNames());
        country.getSelectionModel().select(0);
        country.getSelectionModel().selectedItemProperty().addListener(c -> handleCountryChange());
        handleCountryChange();
    }

    private void initializeUserComboBox() {
        UserDAO dao = new UserDAO();
        createdByUserName.setItems(dao.findAllUserNames());
    }

    @FXML
    private void handleCountryChange() {
        DivisionDAO  divisionDao = new DivisionDAO();
        CountryDAO countryDAO = new CountryDAO();
        CustomerDataState.setDivisionList(divisionDao.findAllByCountry(countryDAO.findCountryId(country.getSelectionModel().getSelectedItem())));
        division.setItems(CustomerDataState.getDivisionNames());
    }

    @FXML
    private void handleSaveCustomer() {
        // TODO: 12/11/2020 implement update customer vs add customer: 
        // TODO: 12/11/2020 implement add customer > view
        String createdBy =  createdByUserName.getSelectionModel().getSelectedItem();
        Division selectedDivision = CustomerDataState.getDivisionList().filtered(s -> s.getDivisionName().matches(division.getSelectionModel().getSelectedItem())).get(0);
        CustomerDAO cxDao = new CustomerDAO();
        Customer newCustomer = new Customer(
                name.getText(),
                address.getText(),
                postalCode.getText(),
                phoneNumber.getText(),
                createdBy,
                CurrentUser.getUserName(),
                selectedDivision.getDivisionId());
        cxDao.create(newCustomer);
        fetchTableData();
        
    }

    private void initializeForm() {
        CustomerDataState.FormType formType = CustomerDataState.getCurrentFormType();
        switch (formType) {
            case ADD:
                clearForm();
                setDisable(false);
                break;
            case EDIT:
                populateForm();
                setDisable(false);
                break;
            case VIEW:
                populateForm();
                setDisable(true);
                break;
            default:
                break;
        }
    }

    private void populateForm() {
        CountryDAO countryDAO = new CountryDAO();
        Customer currentCustomer = CustomerDataState.getSelectedCustomer();

        customerId.setText(String.valueOf(currentCustomer.getCustomerId()));
        name.setText(currentCustomer.getCustomerName());
        phoneNumber.setText(currentCustomer.getPhoneNumber());
        address.setText(currentCustomer.getAddress());
        postalCode.setText(currentCustomer.getAddress());
        createdByUserName.getSelectionModel().select(currentCustomer.getCreatedByUserName());
        String countryName = countryDAO.findCountryNameByDivisionID(currentCustomer.getDivisionId());
        country.getSelectionModel().select(countryName);
        String divisionName = CustomerDataState.getDivisionList().filtered(s -> s.getDivisionId() == currentCustomer.getDivisionId()).get(0).getDivisionName();
        division.getSelectionModel().select(divisionName);
    }


    /**
     * Sets the <code>.setDisable()</code> property of controls in the view.
     * A value of false sets all appropriate forms to be enabled, and a value of true sets all forms to be disabled.
     * @param bool
     * Value to be set for the disable property.
     */
    private void setDisable(boolean bool) {
        name.setDisable(bool);
        phoneNumber.setDisable(bool);
        address.setDisable(bool);
        postalCode.setDisable(bool);
        createdByUserName.setDisable(bool);
        country.setDisable(bool);
        division.setDisable(bool);
        saveButton.setDisable(bool);

    }


    private void clearForm() {
        customerId.setText("Auto-Generated...");
        name.setText("");
        phoneNumber.setText("");
        address.setText("");
        postalCode.setText("");
        createdByUserName.getSelectionModel().select(null);
        country.getSelectionModel().select(0);
        division.getSelectionModel().select(null);

    }
}