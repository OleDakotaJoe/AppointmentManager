package me.stevensheaves.database.utils;

import java.sql.Connection;

public abstract class DataAccessObject<T> {

    /**
     * A field which will contain the database connection.
     */
    protected final Connection connection = DataBaseConnectionExecutor.getConnection();

    /**
     * Abstract method for finding a row in the database.
     * @param id Corresponds to the Primary Key used for the query
     * @return Returns a Data Transfer Object of type <code>T</code>
     */
    public abstract T find(int id);

    /**
     * Abstract method for updating a row in the database.
     * @param dto An object of type <code>T</code> which will be used to pass data to function.
     * @return Returns true if row was updated, false otherwise.
     */
    public abstract boolean update(T dto);

    /**
     * Abstract method for inserting a row into database.
     * @param dto An object of type <code>T</code> which will be used to pass data to function.
     * @return Returns true if row was inserted, false otherwise.
     */
    public abstract boolean create(T dto);

    /**
     * Abstract method for deleting a row from database.
     * @param id the id of the object to be deleted
     */
    public abstract void delete(int id);
}
