package me.stevensheaves.view.controllers.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import me.stevensheaves.data.model.Appointment;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.function.Predicate;

public class AppointmentDataState {
    private static AppointmentDataState.FormType currentFormType;
    private static final ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> todayAppointments = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> thisWeekAppointments = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> thisMonthAppointments = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> pastAppointments = FXCollections.observableArrayList();
    private static final FilteredList<Appointment> filteredAppointmentList = new FilteredList(allAppointments);
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
        AppointmentDataState.todayAppointments.setAll(getAllAppointments().filtered(s-> s.getStartDateTime().isBefore(ZonedDateTime.now().plusDays(1)) && s.getStartDateTime().isAfter(ZonedDateTime.now())));
        AppointmentDataState.thisWeekAppointments.setAll(getAllAppointments().filtered(s-> s.getStartDateTime().isBefore(ZonedDateTime.now().plusWeeks(1)) && s.getStartDateTime().isAfter(ZonedDateTime.now())));
        AppointmentDataState.thisMonthAppointments.setAll(getAllAppointments().filtered(s -> s.getStartDateTime().isBefore(ZonedDateTime.now().plusMonths(1)) && s.getStartDateTime().isAfter(ZonedDateTime.now())));
        AppointmentDataState.pastAppointments.setAll(getAllAppointments().filtered(s -> s.getStartDateTime().isBefore(ZonedDateTime.now())));
    }

    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    public static void setSelectedAppointment(Appointment selectedAppointment) {
        AppointmentDataState.selectedAppointment = selectedAppointment;
    }

    public static FilteredList<Appointment> getFilteredAppointmentList() {
        return filteredAppointmentList;
    }


    public static ObservableList<Appointment> getTodayAppointments() {
        return todayAppointments;
    }

    public static ObservableList<Appointment> getThisWeekAppointments() {
        return thisWeekAppointments;
    }

    public static ObservableList<Appointment> getThisMonthAppointments() {
        return thisMonthAppointments;
    }

    public static ObservableList<Appointment> getPastAppointments() {
        return pastAppointments;
    }

    public enum FormType {
        ADD,
        EDIT,
        VIEW
    }
}
