package me.stevensheaves.database.utils;

import java.sql.Connection;

public abstract class DataAccessObject<T> {

    protected final Connection connection;

    public DataAccessObject(){
        // TODO: 12/4/2020 determine whether or not the below code is actually necessary
        //super();
        this.connection = DataBaseConnectionExecutor.getConnection();
    }

    public abstract T find(int id);
    public abstract boolean update(T dto);
    public abstract boolean create(T dto);
    public abstract void delete(int id);
}
