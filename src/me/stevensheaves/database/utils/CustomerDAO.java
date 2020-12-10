package me.stevensheaves.database.utils;

import me.stevensheaves.data.model.Customer;


import java.util.List;

public class CustomerDAO extends DataAccessObject<Customer>{

    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer find(int id) {
        return null;
    }

    @Override
    public boolean update(Customer dto) {
        return false;
    }

    @Override
    public boolean create(Customer dto) {
        return false;
    }

    @Override
    public void delete(int id) {

    }
}
