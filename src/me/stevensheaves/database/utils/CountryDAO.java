package me.stevensheaves.database.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO extends DataAccessObject<Country> {
    private final String GET_ALL_WITH_DIVISIONS = "SELECT countries.Country, countries.Country_ID FROM countries" +
            " INNER JOIN first_level_divisions ON countries.Country_ID=first_level_divisions.Country_ID GROUP BY Country_ID";
    private final String GET_COUNTRY_BY_DIVISION_ID = "SELECT countries.Country FROM countries INNER JOIN first_level_divisions ON countries.Country_ID = (SELECT Country_ID FROM first_level_divisions WHERE Division_ID= ?) GROUP BY Country;";
    private final String GET_COUNTRY_ID_BY_COUNTRY_NAME= "SELECT countries.Country_ID FROM countries WHERE Country = ?";


    public String findCountryNameByDivisionID(int divisionId) {
        try (PreparedStatement statement = connection.prepareStatement(GET_COUNTRY_BY_DIVISION_ID)) {
            statement.setInt(1, divisionId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("Country");
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public ObservableList<Country> findAll() {
        ObservableList<Country> tempList= FXCollections.observableArrayList();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_WITH_DIVISIONS)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Country country = new Country( rs.getInt("Country_ID"), rs.getString("Country"));
                tempList.add(country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tempList;
    }

    public int findCountryId(String countryName) {
        try (PreparedStatement statement = connection.prepareStatement(GET_COUNTRY_ID_BY_COUNTRY_NAME)) {
            statement.setString(1, countryName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) return rs.getInt("Country_ID");
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
    @Override
    public Country find(int id) {
        return null;
    }

    @Override
    public boolean update(Country dto) {
        return false;
    }

    @Override
    public boolean create(Country dto) {
        return  false;
    }

    @Override
    public void delete(int id) {

    }
}
