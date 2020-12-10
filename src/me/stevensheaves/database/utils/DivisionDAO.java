package me.stevensheaves.database.utils;

import me.stevensheaves.data.model.Division;

public class DivisionDAO extends DataAccessObject<Division> {
    @Override
    public Division find(int id) {
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
