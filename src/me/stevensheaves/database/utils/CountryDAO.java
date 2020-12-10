package me.stevensheaves.database.utils;

import me.stevensheaves.data.model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO extends DataAccessObject<Country> {
    private final String GET_ALL = "SELECT * FROM countries";

    public List<Country> findAll() {
        List<Country> tempList= new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
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
