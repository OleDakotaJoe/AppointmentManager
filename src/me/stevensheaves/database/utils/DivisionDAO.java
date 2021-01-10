package me.stevensheaves.database.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Country;
import me.stevensheaves.data.model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * This class follows the Data Access Object design pattern, and is used for retrieving all data from a database, that pertains to an Division.
 */
public class DivisionDAO extends DataAccessObject<Division> {
    private final String GET_ALL_BY_COUNTRY = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = ?";
    private final String GET_BY_ID = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
    
    
    /**
     * Queries the database for the <code>Country</code> that has a foreign key that corresponds to a specified <code>Division_ID</code>.
     * @param id The <code>Division_ID</code> parameter in the database query.
     * @return Returns an <code>ObservableList</code> of all <code>Division</code>s that have a foreign key that corresponds to the specified <code>Division_ID</code>.
     */
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
    
    /**
     * Queries the "divisions" table whose primary key: <code>Division_ID</code> matches the supplied <code>id</code>.
     * @param id The <code>Division_ID</code> parameter in the database query.
     * @return Returns the <code>Division</code> object that corresponds to the specified <code>Division_ID</code>.
     */
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
    /**
     * Dummy Method
     * @param dto
     * @return
     */
    @Override
    public boolean update(Division dto) {
        return false;
    }
    /**
     * Dummy Method
     * @param dto
     * @return
     */
    @Override
    public boolean create(Division dto) {
        return false;
    }
    /**
     * Dummy Method
     * @param id
     */
    @Override
    public void delete(int id) {

    }
}
