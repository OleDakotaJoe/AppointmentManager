package me.stevensheaves.data.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountriesList {
    private  ObservableList<Country> countries = FXCollections.observableArrayList();

    public CountriesList(ObservableList<Country> countries) {
        this.countries = countries;
    }

    public ObservableList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ObservableList<Country> countries) {
        this.countries = countries;
    }

    public void print() {
        for (Country country: countries){
            System.out.println(country);
        }
    }
}
