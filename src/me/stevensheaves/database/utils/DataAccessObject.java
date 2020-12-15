package me.stevensheaves.database.utils;

import java.sql.Connection;

public abstract class DataAccessObject<T> {

    protected final Connection connection;

    public DataAccessObject(){
        this.connection = DataBaseConnectionExecutor.getConnection();
    }

    public abstract T find(int id);
    public abstract boolean update(T dto);
    public abstract boolean create(T dto);
    public abstract void delete(int id);
}
