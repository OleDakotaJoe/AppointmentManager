package me.stevensheaves.view.controllers.appointment;

import javafx.beans.InvalidationListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import me.stevensheaves.custom.utils.DateTimeCellFormatter;
import me.stevensheaves.data.model.Appointment;
import me.stevensheaves.database.utils.AppointmentDAO;
import me.stevensheaves.view.controllers.state.AppointmentDataState;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

// TODO: 12/12/2020 FINISH UPDATING JAVADOCS

public class AppointmentController {
    @FXML private Button
            appointmentsButton,
            contactsButton,
            reportsButton,
            customersButton,
            dashboardButton,
            addAppointmentButton,
            editAppointmentButton,
            viewAppointmentButton;
    @FXML private BorderPane appointmentPane;
    @FXML private TableView<Appointment> appointmentTable;
    @FXML private TableColumn<Appointment, String> title;
    @FXML private TableColumn<Appointment, String> description;
    @FXML private TableColumn<Appointment, String> appointmentLocation;
    @FXML private TableColumn<Appointment, String> contact;
    @FXML private TableColumn<Appointment, String> type;
    @FXML private TableColumn<Appointment, ZonedDateTime> startTime;
    @FXML private TableColumn<Appointment, ZonedDateTime> endTime;
    @FXML private TableColumn<Appointment, Integer> appointmentId;
    @FXML private TableColumn<Appointment, Integer> customerId;
    @FXML private RadioButton allAppointments, pastAppointments, todayAppointments, weekAppointments, monthAppointments;

    /**
     * Retrieves and sets table data during the initialization of the instance of this class.
     */
    @FXML
    private void initialize() {
        setTableData();
        handleAppointmentListChange();
    }


    /**
     * Utility function for making a call to the database, and setting the <code>allCustomers</code> <code>ObservableList</code>'s data.
     * This method is used anytime a change is made to the database, which needs to be updated in the <code>customersTable</code> element in the
     * <code>customers.fxml</code> view.
     */
    private void fetchTableData() {
        AppointmentDAO dao = new AppointmentDAO();
        AppointmentDataState.setAllAppointments(dao.findAll());
    }

    /**
     * Sets the values for each column in the <code>appointmentTable</code>.
     */
    @FXML
    private void setTableData() {
        appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        startTime.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        startTime.setCellFactory(new DateTimeCellFormatter<>("h:mm a z M/d/yy"));
        endTime.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        endTime.setCellFactory(new DateTimeCellFormatter<>("h:mm a z M/d/yy"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentTable.setItems(AppointmentDataState.getFilteredAppointmentList());
    }


    /**
     * Filters the appointmentList based on which radio button is clicked.
     * Lambda expressions are used here because of their clarity and natural use in setting the predicate of a FilteredList.
     */
    @FXML
    private void handleAppointmentListChange() {
        fetchTableData();
        if (pastAppointments.isSelected()) {
            AppointmentDataState.getFilteredAppointmentList()
                    .setPredicate(s -> s.getStartDateTime().isBefore(ZonedDateTime.now()));
        }
        if (allAppointments.isSelected()) {
            AppointmentDataState.getFilteredAppointmentList()
                    .setPredicate(s -> true);
        }
        if (todayAppointments.isSelected()) {
            AppointmentDataState.getFilteredAppointmentList()
                    .setPredicate(s-> s.getStartDateTime().isBefore(ZonedDateTime.now().plusDays(1)) && s.getStartDateTime().isAfter(ZonedDateTime.now()));
        }
        if (weekAppointments.isSelected()) {
            AppointmentDataState.getFilteredAppointmentList()
                    .setPredicate(s-> s.getStartDateTime().isBefore(ZonedDateTime.now().plusWeeks(1)) && s.getStartDateTime().isAfter(ZonedDateTime.now()));
        }
        if (monthAppointments.isSelected()) {
            AppointmentDataState.getFilteredAppointmentList()
                    .setPredicate(s -> s.getStartDateTime().isBefore(ZonedDateTime.now().plusMonths(1)) && s.getStartDateTime().isAfter(ZonedDateTime.now()));

        }

    }

    /**
     * Utility method for changing scenes.
     * @param event Passed by the ActionEvent which calls this method.
     * @throws IOException If the Scene cannot be loaded, throws an IOException.
     */
    @FXML
    private void changeScene(ActionEvent event) throws IOException {
        if(event.getSource().equals(appointmentsButton)) {
            SceneChanger.changeScene(SceneNames.APPOINTMENT);
        }
        if(event.getSource().equals(contactsButton)) {
            SceneChanger.changeScene(SceneNames.CONTACTS);
        }
        if(event.getSource().equals(customersButton)) {
            SceneChanger.changeScene(SceneNames.CUSTOMERS);
        }
        if(event.getSource().equals(reportsButton)) {
            SceneChanger.changeScene(SceneNames.REPORTS);
        }
        if(event.getSource().equals(dashboardButton)) {
            SceneChanger.changeScene(SceneNames.DASHBOARD);
        }
        if(event.getSource().equals(addAppointmentButton)) {
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.ADD);
            SceneChanger.addChildScene(SceneNames.APPOINTMENT_FORM, appointmentPane);
        }
        if(event.getSource().equals(editAppointmentButton)) {
            if(!isValidSelection()) return;
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.EDIT);
            AppointmentDataState.setSelectedAppointment(appointmentTable.getSelectionModel().getSelectedItem());
            SceneChanger.addChildScene(SceneNames.APPOINTMENT_FORM, appointmentPane);
        }
        if(event.getSource().equals(viewAppointmentButton)) {
            if(!isValidSelection()) return;
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.VIEW);
            AppointmentDataState.setSelectedAppointment(appointmentTable.getSelectionModel().getSelectedItem());
            SceneChanger.addChildScene(SceneNames.APPOINTMENT_FORM, appointmentPane);
        }
    }

    /**
     * Deletes the selected customer.
     */
    @FXML
    private void deleteAppointment() {
        if(!isValidSelection()) return;
        Appointment selected = appointmentTable.getSelectionModel().getSelectedItem();
        ButtonType buttonType = showDeleteConfirmation(selected);
        if(buttonType.equals(ButtonType.CANCEL)) return;

        AppointmentDAO dao = new AppointmentDAO();
        dao.delete(selected.getAppointmentId());
        handleAppointmentListChange();
    }

    @FXML
    private void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Need Some help?");
        alert.setHeaderText("We've got your back. ");
        alert.setContentText("\n\n\t\t\t\tTo Add An Entry\n Click the \"Add\" button. Make sure all fields have been filled out. Note: you cannot choose the ID, it will be" +
                "automatically generated." +
                "\n\n\t\t\t\tTo Edit an Entry\nFirst click on the entry you would like to edit, then click \"Edit\". Afterwards, " +
                "you may edit any field which needs to be updated. When you are finished, click \"Save\". If you would like to discard changes, you may click \"cancel\" " +
                "\n\n\t\t\t\tTo View an Entry\nFirst click on the entry you would like to view, then click \"View\". If you click " +
                "\"View\" all fields will be disabled. If you would like to discard changes, you may click \"cancel\" " +
                "\n\n\t\t\t\tTo Delete an Entry\nFirst click on the entry you would like to delete, then click \"Delete\". You " +
                "will see a pop-up to confirm that you would like to delete the entry. Only click \"OK\" if you are certain that you would like to delete the entry." +
                "\n\n\t\t\t\tTo Filter Entries\nYou may filter the table by clicking the appropriate radio button, above the appointment table." +
                " For example, if you click the radio button next to \"Today\", you will only see appointments scheduled withing 24 hours.");
        alert.show();
    }
    /**
     * Checks whether the user has selected a customer from the table
     * @return
     * Returns true if a customer is selected, false otherwise.
     * Returns false and shows a alert if no customer is selected, or if table is empty.
     */
    private boolean isValidSelection() {
        if(AppointmentDataState.getAllAppointments().isEmpty()) {
            showEmptyListAlert();
            return false;
        }

        int index = appointmentTable.getSelectionModel().getSelectedIndex();
        if(index < 0) {
            showNoAppointmentSelectedAlert();
            return false;
        }
        return true;
    }

    /**
     * Shows an Alert dialog that informs the user that the list of customers is empty.
     */
    private void showEmptyListAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No appointments");
        alert.setHeaderText("You haven't added any appointments yet.");
        alert.setContentText("Before you can complete this action, you must first add a appointment to the database. Try adding " +
                "an appointment before proceeding.");
        alert.show();
    }

    /**
     * Shows an Alert dialog that confirms that the user wants to delete the selected contact.
     * @param appointment
     * When calling this function, the appointment object to be deleted is passed in, to inform the user which appointment is being deleted.
     * @return
     * Returns the button type that the user clicks, so that the method which calls it may determine whether or not to proceed.
     */
    private ButtonType showDeleteConfirmation(Appointment appointment) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Appointment");
        alert.setHeaderText("Are you sure you want to delete this appointment?");
        alert.setContentText("This action cannot be reversed. Only proceed if you are absolutely sure you want to delete the appointment. \n" +
                " \t\tTitle: " + appointment.getTitle()+"\n" +
                "\t\tType: " + appointment.getType());
        alert.showAndWait();
        return alert.getResult();
    }
    /**
     * Shows an Alert dialog that informs the user that no contact is selected.
     */
    private void showNoAppointmentSelectedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No appointment selected");
        alert.setHeaderText("You didn't select a appointment");
        alert.setContentText("Before you can complete this action, you must first select a appointment. Try clicking on " +
                "an appointment before proceeding.");
        alert.show();
    }
}
