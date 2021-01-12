package me.stevensheaves.view.controllers.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import me.stevensheaves.data.model.Appointment;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.function.Predicate;


/**
 * This class is intended for holding state data as related to the appointment.fxml and apppointmentform.fxml views.
 */
public class AppointmentDataState {
    private static AppointmentDataState.FormType currentFormType;
    private static final ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> todayAppointments = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> thisWeekAppointments = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> thisMonthAppointments = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> pastAppointments = FXCollections.observableArrayList();
    private static final FilteredList<Appointment> filteredAppointmentList = new FilteredList(allAppointments);
    private static Appointment selectedAppointment;

    /**
     * Getter for the currentFormType
     * @return returns the value held in the field: currentFormType.
     */
    public static AppointmentDataState.FormType getCurrentFormType() {
        return currentFormType;
    }


    /**
     * Setter for the CurrentFormType field.
     * @param currentFormType The FormType to be set as currentFormType
     */
    public static void setCurrentFormType(AppointmentDataState.FormType currentFormType) {
        AppointmentDataState.currentFormType = currentFormType;
    }

    /**
     * Getter for the list of allAppointments.
     * @return Returns an ObservableList of allAppointments.
     */
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    /**
     * Sets the all of the appointmentlist fields.
     * This method contains a lambda expression for each filtered list, because it was the most succinct and clean way to set the predicate of the filtered list.
     * @param allAppointments the list of appointments to be set.
     */
    public static void setAllAppointments(ObservableList<Appointment> allAppointments) {
        AppointmentDataState.allAppointments.setAll(allAppointments);
        AppointmentDataState.todayAppointments.setAll(getAllAppointments().filtered(s-> s.getStartDateTime().isBefore(ZonedDateTime.now().plusDays(1)) && s.getStartDateTime().isAfter(ZonedDateTime.now())));
        AppointmentDataState.thisWeekAppointments.setAll(getAllAppointments().filtered(s-> s.getStartDateTime().isBefore(ZonedDateTime.now().plusWeeks(1)) && s.getStartDateTime().isAfter(ZonedDateTime.now())));
        AppointmentDataState.thisMonthAppointments.setAll(getAllAppointments().filtered(s -> s.getStartDateTime().isBefore(ZonedDateTime.now().plusMonths(1)) && s.getStartDateTime().isAfter(ZonedDateTime.now())));
        AppointmentDataState.pastAppointments.setAll(getAllAppointments().filtered(s -> s.getStartDateTime().isBefore(ZonedDateTime.now())));
    }

    /**
     * Getter for the selectedAppointment field.
     * @return Returns the Appointment set as the selectedAppointment
     */
    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    /**
     * Setter for the selectedAppointment field.
     * @param selectedAppointment the Appointment to be set as the selectedAppointment.
     */
    public static void setSelectedAppointment(Appointment selectedAppointment) {
        AppointmentDataState.selectedAppointment = selectedAppointment;
    }

    /**
     * Getter for the filteredAppointmentList
     * @return Returns the <code>filteredAppointmentList</code>
     */
    public static FilteredList<Appointment> getFilteredAppointmentList() {
        return filteredAppointmentList;
    }

    /**
     * Getter for the todayAppointments
     * @return Returns the <code>todayAppointments</code>
     */
    public static ObservableList<Appointment> getTodayAppointments() {
        return todayAppointments;
    }
    /**
     * Getter for the thisWeekAppointments
     * @return Returns the <code>thisWeekAppointments</code>
     */
    public static ObservableList<Appointment> getThisWeekAppointments() {
        return thisWeekAppointments;
    }
    /**
     * Getter for the thisMonthAppointments
     * @return Returns the <code>thisMonthAppointments</code>
     */
    public static ObservableList<Appointment> getThisMonthAppointments() {
        return thisMonthAppointments;
    }
    /**
     * Getter for the pastAppointments
     * @return Returns the <code>pastAppointments</code>
     */
    public static ObservableList<Appointment> getPastAppointments() {
        return pastAppointments;
    }

    /**
     * This <code>enum</code> is for distinguishing between what kind of form should be loaded.
     */
    public enum FormType {
        ADD,
        EDIT,
        VIEW
    }
}
