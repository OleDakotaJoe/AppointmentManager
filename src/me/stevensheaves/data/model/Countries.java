package me.stevensheaves.data.model;

import java.util.List;

public class Countries {
    private List<Country> allCountries;

    public List<Country> getAllCountries() {
        return allCountries;
    }

    public void setAllCountries(List<Country> allCountries) {
        this.allCountries = allCountries;
    }

    public void print() {
        for (Country country: allCountries){
            System.out.println(country);
        }
    }
}
