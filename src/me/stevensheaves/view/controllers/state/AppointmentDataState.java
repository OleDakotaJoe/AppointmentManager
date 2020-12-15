package me.stevensheaves.view.controllers.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Appointment;

public class AppointmentDataState {
    private static AppointmentDataState.FormType currentFormType;
    private static final ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static Appointment selectedAppointment;

    public static AppointmentDataState.FormType getCurrentFormType() {
        return currentFormType;
    }

    public static void setCurrentFormType(AppointmentDataState.FormType currentFormType) {
        AppointmentDataState.currentFormType = currentFormType;
    }

    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public static void setAllAppointments(ObservableList<Appointment> allAppointments) {
        AppointmentDataState.allAppointments.setAll(allAppointments);
    }

    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    public static void setSelectedAppointment(Appointment selectedAppointment) {
        AppointmentDataState.selectedAppointment = selectedAppointment;
    }

    public enum FormType {
        ADD,
        EDIT,
        VIEW
    }
}
