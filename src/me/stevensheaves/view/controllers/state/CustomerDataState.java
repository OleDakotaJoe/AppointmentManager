package me.stevensheaves.view.controllers.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Contact;
import me.stevensheaves.data.model.Country;
import me.stevensheaves.data.model.Customer;
import me.stevensheaves.data.model.Division;

/**
 * This class is intended for holding state data as related to the customers.fxml and customersform.fxml views.
 */
public class CustomerDataState {

    private static CustomerDataState.FormType currentFormType;
    private static final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static final ObservableList<Country> countryList = FXCollections.observableArrayList();
    private static final ObservableList<Division> divisionList= FXCollections.observableArrayList();
    private static Customer selectedCustomer;

    /**
     * Getter for the currentFormType
     * @return returns the value held in the field: currentFormType.
     */
    public static CustomerDataState.FormType getCurrentFormType() {
        return currentFormType;
    }

    /**
     * Setter for the currentFormType field.
     * @param currentFormType The FormType to be set as currentFormType
     */
    public static void setCurrentFormType(CustomerDataState.FormType currentFormType) {
        CustomerDataState.currentFormType = currentFormType;
    }

    /**
     * Getter for the list of allCustomers.
     * @return Returns an ObservableList of allCustomers.
     */
    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    /**
     * Sets the all of the allCustomers field.
     * @param allCustomers The list of Customers to be set.
     */
    public static void setAllCustomers(ObservableList<Customer> allCustomers) {
        CustomerDataState.allCustomers.setAll(allCustomers);
    }
    /**
     * Getter for the list of countryList.
     * @return Returns an ObservableList of countryList.
     */
    public static ObservableList<Country> getCountryList() {
        return countryList;
    }

    /**
     * Sets the all of the countryList field.
     * @param countryList The list of Contacts to be set.
     */
    public static void setCountryList(ObservableList<Country> countryList) {
        CustomerDataState.countryList.setAll(countryList);
    }

    /**
     * Getter for the list of divisionList.
     * @return Returns an ObservableList of divisionList.
     */
    public static ObservableList<Division> getDivisionList() {
        return divisionList;
    }

    /**
     * Sets the all of the divisionList field.
     * @param divisionList The list of Contacts to be set.
     */
    public static void setDivisionList(ObservableList<Division> divisionList) {
        CustomerDataState.divisionList.setAll(divisionList);
    }

    /**
     * Getter for the selectedCustomer field.
     * @return Returns the Contact set as the selectedCustomer
     */
    public static Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    /**
     * Setter for the selectedCustomer field.
     * @param selectedCustomer the Customer to be set as the selectedCustomer.
     */
    public static void setSelectedCustomer(Customer selectedCustomer) {
        CustomerDataState.selectedCustomer = selectedCustomer;
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
