package me.stevensheaves.view.controllers.appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import me.stevensheaves.custom.controls.TextFieldLimited;
import me.stevensheaves.custom.utils.TimeUtilities;
import me.stevensheaves.data.model.*;
import me.stevensheaves.database.utils.*;
import me.stevensheaves.view.controllers.state.AppointmentDataState;
import me.stevensheaves.view.controllers.state.ContactDataState;
import me.stevensheaves.view.controllers.state.CustomerDataState;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;
import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;

// TODO: 12/23/2020 Finish Javadocs
public class AppointmentFormController {

    @FXML
    private TextField appointmentId;
    @FXML
    private GridPane mainPane;
    @FXML
    private TextFieldLimited title, description, appointmentLocation, appointmentType;
    @FXML
    private ComboBox<String> startHour, startMinute, endHour, endMinute,userName;
    @FXML
    private ComboBox<Customer> customerName;
    @FXML
    private ComboBox<Contact> contactName;
    @FXML
    private DatePicker startDate,endDate;
    @FXML
    private Button saveButton;

    /**
     * Initializes the class with appropriate data.
     * Called when the class is instatiated.
     */
    @FXML
    private void initialize() {
        setTimePickerValues();
        setCustomerNameComboBoxValues();
        setUserNameComboBoxValues();
        setContactComboBoxValues();
        initializeForm();
    }

    /**
     * Retrieves all appointments from database and sets them as the ObservableArrayList Which is the PropertyValueFactory for the appointmentTable in the appointments.fxml view.
     */
    private void fetchTableData() {
        AppointmentDataState.setAllAppointments(new AppointmentDAO().findAll());
    }

    /**
     * Sets the values for the <code>startMinute</code>,<code>startHour</code>,<code>endMinute</code>, and <code>endHour</code> <code>ComboBox</code>es
     */
    @FXML
    private void setTimePickerValues() {
        startMinute.setItems(FXCollections.observableArrayList("00","15","30","45"));
        startHour.setItems(FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"));
        endMinute.setItems(FXCollections.observableArrayList("00","15","30","45"));
        endHour.setItems(FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"));
    }

    @FXML
    private void setCustomerNameComboBoxValues() {
        CustomerDAO dao = new CustomerDAO();
        CustomerDataState.setAllCustomers(dao.findAll());
        customerName.setItems(CustomerDataState.getAllCustomers());
    }

    @FXML
    private void setUserNameComboBoxValues() {
        UserDAO dao = new UserDAO();
        userName.setItems(dao.findAllUserNames());
    }

    @FXML
    private void setContactComboBoxValues() {
        ContactDAO dao = new ContactDAO();
        ContactDataState.setAllContacts(dao.findAll());
        contactName.setItems(ContactDataState.getAllContacts());
    }


    @FXML
    private void handleSaveAppointment() {
        if(!isFormComplete()) return;
        AppointmentDAO dao = new AppointmentDAO();
        AppointmentDataState.FormType typeOfForm = AppointmentDataState.getCurrentFormType();
        Appointment appointment;
        boolean isSaved;
        switch (typeOfForm) {
            case ADD:
                appointment = new Appointment(
                        title.getText(),
                        description.getText(),
                        appointmentLocation.getText(),
                        appointmentType.getText(),
                        getStartDateTime(),
                        getEndDateTime(),
                        ZonedDateTime.now(),
                        CurrentUser.getUserName(),
                        ZonedDateTime.now(),
                        CurrentUser.getUserName(),
                        customerName.getValue().getCustomerId(),
                        new UserDAO().find(String.valueOf(userName.getValue())).getId() ,
                        contactName.getValue().getId()
                );
                isSaved = dao.create(appointment);
                if (isSaved) {
                    AppointmentDataState.setSelectedAppointment(dao.findLast());
                    AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.VIEW);
                    fetchTableData();
                    try {
                        SceneChanger.addChildScene(SceneNames.APPOINTMENT_FORM,((BorderPane) mainPane.getParent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case EDIT:
               appointment = new Appointment(
                       Integer.parseInt(appointmentId.getText()),
                       title.getText(),
                       description.getText(),
                       appointmentLocation.getText(),
                       appointmentType.getText(),
                       getStartDateTime(),
                       getEndDateTime(),
                       ZonedDateTime.now(),
                       CurrentUser.getUserName(),
                       ZonedDateTime.now(),
                       CurrentUser.getUserName(),
                       customerName.getValue().getCustomerId(),
                       new UserDAO().find(String.valueOf(userName.getValue())).getId(),
                       contactName.getValue().getId()
               );
                isSaved = dao.update(appointment);
                if (isSaved) {
                    AppointmentDataState.setSelectedAppointment(appointment);
                    AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.VIEW);
                    fetchTableData();
                    try {
                        SceneChanger.addChildScene(SceneNames.APPOINTMENT_FORM,((BorderPane) mainPane.getParent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                return;
        }
    }


    private ZonedDateTime getStartDateTime() {
        return new TimeUtilities().ZonedDateTimeBuilder(startDate.getValue(),startHour.getValue(),startMinute.getValue());

    }

    private ZonedDateTime getEndDateTime() {
        return new TimeUtilities().ZonedDateTimeBuilder(endDate.getValue(),endHour.getValue(),endMinute.getValue());
    }



/**
     * Method for initializing the Form data.
     * Switch method which calls specific methods appropriate to each FormType.
     * FormType is first checked, and then the appropriate methods are called depending on which type the current form is.
     */

    private void initializeForm() {
        AppointmentDataState.FormType formType = AppointmentDataState.getCurrentFormType();
        switch (formType) {
            case ADD:
                clearForm();
                setDisabledFields(false);
                break;
            case EDIT:
                populateForm();
                setDisabledFields(false);
                break;
            case VIEW:
                populateForm();
                setDisabledFields(true);
                break;
            default:
                break;
        }
    }

    private void populateForm() {
        ContactDAO contactDAO = new ContactDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        UserDAO userDAO = new UserDAO();
        Appointment currentAppointment = AppointmentDataState.getSelectedAppointment();
        appointmentId.setText(String.valueOf(currentAppointment.getAppointmentId()));
        title.setText(currentAppointment.getTitle());
        customerName.setValue(customerDAO.find(currentAppointment.getCustomerId()));
        contactName.setValue(contactDAO.find(currentAppointment.getContactId()));
        userName.setValue(userDAO.find(currentAppointment.getUserId()).getUserName());
        description.setText(currentAppointment.getDescription());
        appointmentLocation.setText(currentAppointment.getLocation());
        appointmentType.setText(currentAppointment.getType());
        startDate.setValue(currentAppointment.getStartDateTime().toLocalDate());
        endDate.setValue(currentAppointment.getEndDateTime().toLocalDate());
        LocalTime startTime = currentAppointment.getStartDateTime().toLocalTime();
        LocalTime endTime = currentAppointment.getEndDateTime().toLocalTime();
        startHour.setValue(DateTimeFormatter.ofPattern("HH").format(startTime));
        endHour.setValue(DateTimeFormatter.ofPattern("HH").format(endTime));
        endMinute.setValue(DateTimeFormatter.ofPattern("mm").format(endTime));
        startMinute.setValue(DateTimeFormatter.ofPattern("mm").format(startTime));

    }



    /**
     * Sets the <code>.setDisable()</code> property of controls in the view.
     * A value of false sets all appropriate forms to be enabled, and a value of true sets all forms to be disabled.
     * @param bool
     * Value to be set for the disable property.
     */
    private void setDisabledFields(boolean bool) {
        title.setDisable(bool);
        customerName.setDisable(bool);
        contactName.setDisable(bool);
        userName.setDisable(bool);
        description.setDisable(bool);
        appointmentLocation.setDisable(bool);
        appointmentType.setDisable(bool);
        endDate.setDisable(bool);
        endHour.setDisable(bool);
        endMinute.setDisable(bool);
        startMinute.setDisable(bool);
        startDate.setDisable(bool);
        startHour.setDisable(bool);
        saveButton.setDisable(bool);


    }


    /**
     * Utility function for clearing all user-defined text from the form.
     */
    private void clearForm() {
        appointmentId.setText("Auto-generated");
        title.setText("");
        customerName.setValue(null);
        contactName.setValue(null);
        userName.setValue(null);
        description.setText("");
        appointmentLocation.setText("");
        appointmentType.setText("");
        endDate.setValue(null);
        endHour.setValue(null);
        endMinute.setValue(null);
        startMinute.setValue(null);
        startDate.setValue(null);
        startHour.setValue(null);

    }


    /**
     * Checks for completeness of the form
     * @return
     * Returns true if form is complete, and false if not.
     */
    private boolean isFormComplete() {
        if(title.getText().isBlank()
                || (customerName.getValue() == null)
                || (contactName.getValue() == null)
                || (userName.getValue() == null)
                || description.getText().isBlank()
                || appointmentLocation.getText().isBlank()
                || appointmentType.getText().isBlank()
                || (startDate.getValue() == null)
                || (startHour.getValue() == null )
                || (startMinute.getValue() == null)
                || (endDate.getValue() == null)
                || (endHour.getValue() == null)
                || (endMinute.getValue() == null)
        ) {
            showFormNotCompleteAlert();
            return false;
        } else if (getEndDateTime().compareTo(getStartDateTime()) < 0) {
            showDateTimeIncorrectAlert();
            return false;
        } else return isAppointmentTimeValid();
    }

    private boolean isAppointmentTimeValid() {
        ZonedDateTime startTime = getStartDateTime();
        ZonedDateTime endTime = getEndDateTime();
        ZonedDateTime startOfBusiness = LocalDateTime.of(startDate.getValue(), LocalTime.parse("08:00") ).atZone(ZoneId.of("America/New_York"));
        ZonedDateTime closeOfBusiness = LocalDateTime.of(startDate.getValue(), LocalTime.parse("22:00") ).atZone(ZoneId.of("America/New_York"));
        ZonedDateTime midnightPM = LocalDateTime.of(startDate.getValue(),LocalTime.parse("23:59") ).atZone(ZoneId.of("America/New_York"));
        ZonedDateTime midnightAM = LocalDateTime.of(startDate.getValue(),LocalTime.parse("00:00") ).atZone(ZoneId.of("America/New_York"));


        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<Appointment> appointmentsByCx = dao.findByCustomerId(customerName.getSelectionModel().getSelectedItem().getCustomerId());
        for (Appointment appointment : appointmentsByCx) {
            //checks all appointments for an overlap.
            if (new TimeUtilities().checkForOverlap(appointment.getStartDateTime(), appointment.getEndDateTime(), startTime, endTime,false)
                && (AppointmentDataState.getCurrentFormType() == AppointmentDataState.FormType.ADD || (appointment.getAppointmentId() != Integer.parseInt(appointmentId.getText()))))
            {
                //if overlap is found, shows appointment overlap, then
                showAppointmentOverlapAlert(appointment);
                return false;
            } else if (new TimeUtilities().checkForOverlap(midnightAM.withZoneSameInstant(ZoneId.of("UTC")),startOfBusiness.withZoneSameInstant(ZoneId.of("UTC")),startTime.withZoneSameInstant(ZoneId.of("UTC")), endTime.withZoneSameInstant(ZoneId.of("UTC")),true)
                    || new TimeUtilities().checkForOverlap(closeOfBusiness.withZoneSameInstant(ZoneId.of("UTC")),midnightPM.withZoneSameInstant(ZoneId.of("UTC")),startTime.withZoneSameInstant(ZoneId.of("UTC")), endTime.withZoneSameInstant(ZoneId.of("UTC")), true)
                    || getEndDateTime().getDayOfWeek() == DayOfWeek.SUNDAY
                    || getEndDateTime().getDayOfWeek() == DayOfWeek.SATURDAY
                    || getStartDateTime().getDayOfWeek() == DayOfWeek.SUNDAY
                    || getStartDateTime().getDayOfWeek() == DayOfWeek.SATURDAY
            ){
                //If appointment overlaps with business hours or falls on a weekend, then
                showAppointmentOutsideBusinessHoursAlert();
                return false;
            }
        }
        return true;
    }

    /**
     * Utility function for alerting the user that the form is not complete.
     */
    private void showAppointmentOverlapAlert(Appointment appointment) {
        Customer customer = new CustomerDAO().find(appointment.getCustomerId());
        String time = DateTimeFormatter.ofPattern("h:mm a z M/d/yy").format(appointment.getStartDateTime()) ;

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Appointment Time Invalid");
        alert.setHeaderText("Appointment time overlaps with another appointment");
        alert.setContentText("The customer, " +customer.getCustomerName() + " (ID:  " + appointment.getCustomerId() + "), already has an appointment scheduled at " + time +". The" +
                " appointment has not been saved. Check with the customer to see if you can arrange another time.");
        alert.show();
    }

    /**
     * Utility function for alerting the user that the form is not complete.
     */
    private void showAppointmentOutsideBusinessHoursAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Appointment Time Invalid");
        alert.setHeaderText("Appointment time falls outside business hours");
        alert.setContentText("Appointment time must fall between business hours. Business hours are from 8AM to 10PM EST, Monday - Friday");
        alert.show();
    }

    /**
     * Utility function for alerting the user that the form is not complete.
     */
    private void showFormNotCompleteAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Form not Complete");
        alert.setHeaderText("All fields are required.");
        alert.setContentText("You have not completed the form. You're content has not been saved. Please complete all required fields then try again. ");
        alert.show();
    }

    /**
     * Utility function for alerting the user that the form is not complete.
     */
    private void showDateTimeIncorrectAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Date/Time is incorrect");
        alert.setHeaderText("End date/time must be after start date/time.");
        alert.setContentText("Either your end date or end time is before the start date or time. You're content has not been saved. Please complete all required fields then try again. ");
        alert.show();
    }
}
