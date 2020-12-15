package me.stevensheaves.database.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Country;
import me.stevensheaves.data.model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DivisionDAO extends DataAccessObject<Division> {
    private final String GET_ALL_BY_COUNTRY = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = ?";
    private final String GET_BY_ID = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";

    public ObservableList<Division> findAllByCountry(int id) {
        ObservableList<Division> tempList= FXCollections.observableArrayList();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_COUNTRY)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Division division = new Division(rs.getInt("Division_ID"),rs.getString("Division"),rs.getInt("Country_ID"));
                tempList.add(division);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tempList;
    }

    @Override
    public Division find(int id) {
            try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    Division division = new Division(rs.getInt("Division_ID"),rs.getString("Division"),rs.getInt("Country_ID"));
                    return division;
                }
            } catch(SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }

        return null;
    }

    @Override
    public boolean update(Division dto) {
        return false;
    }

    @Override
    public boolean create(Division dto) {
        return false;
    }

    @Override
    public void delete(int id) {

    }
}
