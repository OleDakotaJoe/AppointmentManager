package me.stevensheaves.database.utils;

import me.stevensheaves.data.model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountriesDAO extends DataAccessObject<Country> {
    private final String GET_ALL = "SELECT * FROM countries";

    public List<Country> findAll() {
        List<Country> tempList= new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("Country"));
                Country country = new Country(rs.getString("Country"));
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
    public Country update(Country dto) {
        return null;
    }

    @Override
    public Country create(Country dto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
