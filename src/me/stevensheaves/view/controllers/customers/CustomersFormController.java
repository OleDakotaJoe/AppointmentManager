package me.stevensheaves.view.controllers.customers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import me.stevensheaves.custom.controls.TextFieldLimited;
import me.stevensheaves.data.model.*;
import me.stevensheaves.data.utils.Validator;
import me.stevensheaves.database.utils.CountryDAO;
import me.stevensheaves.database.utils.CustomerDAO;
import me.stevensheaves.database.utils.DivisionDAO;
import me.stevensheaves.view.controllers.state.CustomerDataState;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;

// TODO: 12/14/2020 fix blank combobox bug


public class CustomersFormController {
    @FXML private ComboBox<Country> country;
    @FXML private ComboBox<Division> division;
    @FXML private GridPane mainPane;
    @FXML private TextFieldLimited name,phoneNumber,address, postalCode;
    @FXML private TextField customerId;
    @FXML private Button saveButton, cancelButton;


    /**
     * Initializes the forms with or without data, wherever appropriate.
     */
    @FXML
    private void initialize() {
        initializeLocationComboBoxes();
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

/*    private void setSelectedCountryAndDivisionData() {
        CountryDAO countryDAO = new CountryDAO();
        DivisionDAO divisionDAO = new DivisionDAO();
        Division selectedDivision = divisionDAO.find(CustomerDataState.getSelectedCustomer().getDivisionId());
        Country selectedCountry = countryDAO.findCountryByDivisionId(selectedDivision.getDivisionId());
        CustomerDataState.setSelectedCountry(selectedCountry);
        CustomerDataState.setSelectedDivision(selectedDivision);
    }*/

    /**
     * Fetches and sets Data for the country and division ComboBoxes
     */
    private void initializeLocationComboBoxes() {
        CountryDAO countryDAO = new CountryDAO();
        CustomerDataState.setCountryList(countryDAO.findAll());
        country.setItems(CustomerDataState.getCountryList());
        country.getSelectionModel().selectedItemProperty().addListener(c -> handleCountryChange());

    }


    /**
     * Handles an 'onChange' event called when the <code>country</code> ComboBox's input is changed.
     * Sets the list of Division Names by selecting the appropriate names from the database.
     */
    @FXML
    private void handleCountryChange() {
        DivisionDAO  divisionDao = new DivisionDAO();
        CustomerDataState.setDivisionList(divisionDao.findAllByCountry(country.getSelectionModel().getSelectedItem().getId()));
        division.setItems(CustomerDataState.getDivisionList());
        division.setValue(null);

    }

    /**
     * Creates or updates customer data.
     */
    @FXML
    private void handleSaveCustomer() {

        if(!isFormComplete()) return;
        Division selectedDivision = division.getValue();
        CustomerDAO cxDao = new CustomerDAO();
        Customer customer;
        CustomerDataState.FormType typeOfForm = CustomerDataState.getCurrentFormType();
        boolean isSaved;
        switch (typeOfForm) {
            case ADD:
                customer = new Customer(
                        name.getText(),
                        address.getText(),
                        postalCode.getText(),
                        phoneNumber.getText(),
                        CurrentUser.getUserName(),
                        CurrentUser.getUserName(),
                        selectedDivision.getDivisionId());
                isSaved = cxDao.create(customer);
                if (isSaved) CustomerDataState.setSelectedCustomer(cxDao.findLast());
                break;
            case EDIT:
                customer = new Customer(
                        Integer.parseInt(customerId.getText()),
                        name.getText(),
                        address.getText(),
                        postalCode.getText(),
                        phoneNumber.getText(),
                        CurrentUser.getUserName(),
                        CurrentUser.getUserName(),
                        selectedDivision.getDivisionId());
                isSaved = cxDao.update(customer);
                if (isSaved) CustomerDataState.setSelectedCustomer(customer);
                break;
            default:
                return;
        }
            //BELOW IS NECESSARY TO TRIGGER THE INITIALIZATION OF A NEW FORM>
        if (isSaved) {
            CustomerDataState.setCurrentFormType(CustomerDataState.FormType.VIEW);
            try {
                SceneChanger.addChildScene(SceneNames.CUSTOMER_FORM,((BorderPane) mainPane.getParent()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            fetchTableData();

        }

    }

    private void initializeForm() {
        CustomerDataState.FormType formType = CustomerDataState.getCurrentFormType();
        switch (formType) {
            case ADD:
                setDisable(false);
                clearForm();
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
        DivisionDAO divisionDAO = new DivisionDAO();
        Customer currentCustomer = CustomerDataState.getSelectedCustomer();
        customerId.setText(String.valueOf(currentCustomer.getCustomerId()));
        name.setText(currentCustomer.getCustomerName());
        phoneNumber.setText(currentCustomer.getPhoneNumber());
        address.setText(currentCustomer.getAddress());
        postalCode.setText(currentCustomer.getAddress());
        Country selectedCountry = countryDAO.findCountryByDivisionId(currentCustomer.getDivisionId());
        Division selectedDivision = divisionDAO.find(currentCustomer.getDivisionId());
        country.getSelectionModel().select(selectedCountry);
        division.getSelectionModel().select(selectedDivision);
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
        country.setDisable(bool);
        division.setDisable(bool);
        saveButton.setDisable(bool);

    }


    /**
     * Utility function for clearing all user-defined text from the form.
     */
    private void clearForm() {
        customerId.setText("Auto-Generated...");
        name.setText("");
        phoneNumber.setText("");
        address.setText("");
        postalCode.setText("");
        country.getSelectionModel().select(null);
        division.getSelectionModel().select(null);

    }

    /**
     * Removes letters from the field which is the source of the <code>KeyEvent</code>.
     * @param event
     * The <code>KeyEvent</code> which calls the code.
     * Used for determining the <code>.source()</code> of the event, and removing the letters from the user defined input.
     */
    @FXML
    private void removeLettersFromTextField(KeyEvent event) {
        Validator.removeLetters(event);
    }

    /**
     * Checks for completeness of the form
     * @return
     * Returns true if form is complete, and false if not.
     */
    private boolean isFormComplete() {

        boolean isComplete;
        if(name.getText().isBlank()
                || phoneNumber.getText().isBlank()
                || address.getText().isBlank()
                || postalCode.getText().isBlank()
                || (country.getValue() == null )
                || (division.getValue() == null)
        ) {
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