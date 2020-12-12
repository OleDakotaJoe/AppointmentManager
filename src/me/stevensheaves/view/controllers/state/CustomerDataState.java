package me.stevensheaves.view.controllers.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Country;
import me.stevensheaves.data.model.Customer;
import me.stevensheaves.data.model.Division;

public class CustomerDataState {

    private static CustomerDataState.FormType currentFormType;
    private static final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static final ObservableList<Country> countryList = FXCollections.observableArrayList();
    private static final ObservableList<Division> divisionList= FXCollections.observableArrayList();
    private static final ObservableList<String> countryNames = FXCollections.observableArrayList();
    private static final ObservableList<String> divisionNames = FXCollections.observableArrayList();
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
        ObservableList<String> temp = FXCollections.observableArrayList();
        for (Country country : getCountryList()) {
            temp.add(country.getCountryName());
            countryNames.setAll(temp);
        }
    }

    public static ObservableList<Division> getDivisionList() {
        return divisionList;
    }

    public static void setDivisionList(ObservableList<Division> divisionList) {
        CustomerDataState.divisionList.setAll(divisionList);
        ObservableList<String> temp = FXCollections.observableArrayList();
        for (Division division : getDivisionList()) {
            temp.add(division.getDivisionName());
            divisionNames.setAll(temp);
        }
    }

    public static ObservableList<String> getCountryNames() {
        return countryNames;
    }

    public static ObservableList<String> getDivisionNames() {
        return divisionNames;
    }

    public static Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public static void setSelectedCustomer(Customer selectedCustomer) {
        CustomerDataState.selectedCustomer = selectedCustomer;
    }
    // TODO: 12/11/2020 remove the below code if possible
/*    public static Country findCountryByName(String name) {
        for (Country country : getCountryList()) {
            if (country.getCountryName().matches(name)) {
                return country;
            }
        }
        return null;
    }
    */

    public enum FormType {
        ADD,
        EDIT,
        VIEW
    }
}
