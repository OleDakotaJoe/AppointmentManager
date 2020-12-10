package me.stevensheaves.data.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DivisionList {
    private ObservableList<Division> divisions = FXCollections.observableArrayList();

    public DivisionList(ObservableList<Division> divisions) {
        this.divisions = divisions;
    }

    public ObservableList<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(ObservableList<Division> divisions) {
        this.divisions = divisions;
    }
}
