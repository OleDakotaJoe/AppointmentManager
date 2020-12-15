package me.stevensheaves.view.controllers.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Contact;
import me.stevensheaves.data.model.Country;
import me.stevensheaves.data.model.Customer;
import me.stevensheaves.data.model.Division;

public class CustomerDataState {

    private static CustomerDataState.FormType currentFormType;
    private static final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static final ObservableList<Country> countryList = FXCollections.observableArrayList();
    private static final ObservableList<Division> divisionList= FXCollections.observableArrayList();
    private static Customer selectedCustomer;


    public static CustomerDataState.FormType getCurrentFormType() {
        return currentFormType;
    }

    public static void setCurrentFormType(CustomerDataState.FormType currentFormType) {
        CustomerDataState.currentFormType = currentFormType;
    }

    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public static void setAllCustomers(ObservableList<Customer> allCustomers) {
        CustomerDataState.allCustomers.setAll(allCustomers);
    }

    public static ObservableList<Country> getCountryList() {
        return countryList;
    }

    public static void setCountryList(ObservableList<Country> countryList) {
        CustomerDataState.countryList.setAll(countryList);
    }

    public static ObservableList<Division> getDivisionList() {
        return divisionList;
    }

    public static void setDivisionList(ObservableList<Division> divisionList) {
        CustomerDataState.divisionList.setAll(divisionList);
    }


    public static Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public static void setSelectedCustomer(Customer selectedCustomer) {
        CustomerDataState.selectedCustomer = selectedCustomer;
    }


    public enum FormType {
        ADD,
        EDIT,
        VIEW
    }
}
