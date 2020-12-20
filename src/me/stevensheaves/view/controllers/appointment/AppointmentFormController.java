package me.stevensheaves.view.controllers.appointment;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import me.stevensheaves.custom.controls.TextFieldLimited;
import me.stevensheaves.data.model.*;
import me.stevensheaves.data.utils.Validator;
import me.stevensheaves.database.utils.*;
import me.stevensheaves.view.controllers.state.AppointmentDataState;
import me.stevensheaves.view.controllers.state.ContactDataState;
import me.stevensheaves.view.controllers.state.CustomerDataState;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;
import java.time.*;

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

    // TODO: 12/13/2020 go back and fix other inefficient use of comboboxes in customer area.
    @FXML
    private void initialize() {
        setTimePickerValues();
        setCustomerNameComboBoxValues();
        setUserNameComboBoxValues();
        setContactComboBoxValues();
        initializeForm();
    }

    private void fetchTableData() {
        AppointmentDataState.setAllAppointments(new AppointmentDAO().findAll());
    }

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
                    try {
                        SceneChanger.addChildScene(SceneNames.APPOINTMENT_FORM,((BorderPane) mainPane.getParent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fetchTableData();
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
                    AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.VIEW);
                    try {
                        SceneChanger.addChildScene(SceneNames.APPOINTMENT_FORM,((BorderPane) mainPane.getParent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fetchTableData();
                }
                break;
            default:
                return;
        }
    }


    private ZonedDateTime getStartDateTime() {
        LocalDate startDatePart = startDate.getValue();
        LocalTime startTimePart = LocalTime.parse(startHour.getValue() + ":" + startMinute.getValue());
        LocalDateTime localDateTime =  LocalDateTime.of(startDatePart,startTimePart);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC);
        return zonedDateTime;
    }

    private ZonedDateTime getEndDateTime() {
        LocalDate endDatePart = endDate.getValue();
        LocalTime endTimePart = LocalTime.parse(endHour.getValue() + ":"+ endMinute.getValue());
        LocalDateTime localDateTime = LocalDateTime.of(endDatePart,endTimePart);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC);
        return zonedDateTime;
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
        startHour.getSelectionModel().select(startTime.getHour());
        endHour.getSelectionModel().select(endTime.getHour());
        startMinute.getSelectionModel().select(startTime.getMinute());
        endMinute.getSelectionModel().select(endTime.getMinute());


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
     * Removes letters from the field which is the source of the <code>KeyEvent</code>.
     * @param event
     * The <code>KeyEvent</code> which calls the code.
     * Used for determining the <code>.source()</code> of the event, and removing the letters from the user defined input.
     */
    @FXML
    private void removeLettersFromTextField(KeyEvent event) {
        Validator.removeLetters(event);
    }

    /**
     * Checks for completeness of the form
     * @return
     * Returns true if form is complete, and false if not.
     */
    private boolean isFormComplete() {
        boolean isComplete;
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
            isComplete = false;
        } else {
            if (getEndDateTime().compareTo(getStartDateTime()) < 0) {
                showDateTimeIncorrectAlert();
                return false;
            }
            isComplete = true;
        }
        return isComplete;
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

    private void showDateTimeIncorrectAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Date/Time is incorrect");
        alert.setHeaderText("End date/time must be after start date/time.");
        alert.setContentText("Either your end date or end time is before the start date or time. You're content has not been saved. Please complete all required fields then try again. ");
        alert.show();
    }
}
