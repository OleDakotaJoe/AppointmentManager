package me.stevensheaves.data.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomersList {
    private ObservableList<Customer> customers = FXCollections.observableArrayList();

    public CustomersList(ObservableList<Customer> customers) {
        this.customers = customers;
    }

    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ObservableList<Customer> customers) {
        this.customers = customers;
    }
}
