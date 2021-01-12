package me.stevensheaves.database.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class follows the Data Access Object design pattern, and is used for retrieving all data from a database, that pertains to an Country.
 */
public class CountryDAO extends DataAccessObject<Country> {
    private final String GET_ALL_WITH_DIVISIONS = "SELECT countries.Country, countries.Country_ID FROM countries" +
            " INNER JOIN first_level_divisions ON countries.Country_ID=first_level_divisions.Country_ID GROUP BY Country_ID";
    private final String GET_COUNTRY_BY_DIVISION_ID = "SELECT * FROM countries INNER JOIN first_level_divisions ON countries.Country_ID = (SELECT Country_ID FROM first_level_divisions WHERE Division_ID = ?);";

    /**
     * Queries the database for the <code>Country</code> that has a foreign key that corresponds to a specified <code>Division_ID</code>.
     * @param divisionId The <code>Division_ID</code> parameter in the database query.
     * @return Returns an <code>ObservableList</code> of all <code>Appointment</code>s that have a foreign key that corresponds to the specified <code>Division_ID</code>.
     */
    public Country findCountryByDivisionId(int divisionId) {
        try (PreparedStatement statement = connection.prepareStatement(GET_COUNTRY_BY_DIVISION_ID)) {
            statement.setInt(1, divisionId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Country country = new Country( rs.getInt("Country_ID"), rs.getString("Country"));
                return country;
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Finds all <code>Country</code>s, and returns them in an <code>ObservableList</code>
     * @return Returns an <code>ObservableList</code> holding all <code>Country</code>s.
     */
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


    /**
     * Dummy Method
     */
    @Override
    public Country find(int id) {
        return null;
    }
    /**
     * Dummy Method
     */
    @Override
    public boolean update(Country dto) {
        return false;
    }
    /**
     * Dummy Method
     */
    @Override
    public boolean create(Country dto) {
        return  false;
    }
    /**
     * Dummy Method
     */
    @Override
    public void delete(int id) {

    }
}
