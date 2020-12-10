package me.stevensheaves.data.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppointmentsList {
    private  ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    public AppointmentsList(ObservableList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public ObservableList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ObservableList<Appointment> appointments) {
        this.appointments = appointments;
    }
}
