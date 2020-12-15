package me.stevensheaves.view.controllers.appointment;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import me.stevensheaves.custom.controls.TextFieldLimited;
import me.stevensheaves.data.model.*;
import me.stevensheaves.database.utils.AppointmentDAO;
import me.stevensheaves.database.utils.ContactDAO;
import me.stevensheaves.database.utils.CustomerDAO;
import me.stevensheaves.database.utils.UserDAO;
import me.stevensheaves.view.controllers.state.AppointmentDataState;
import me.stevensheaves.view.controllers.state.ContactDataState;
import me.stevensheaves.view.controllers.state.CustomerDataState;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentFormController {

    @FXML
    private TextField appointmentId;
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

    // TODO: 12/13/2020 go back and fix other inefficient use of comboboxes in customer area.
    @FXML
    private void initialize() {
        setTimePickerValues();
        setCustomerNameComboBoxValues();
        setUserNameComboBoxValues();
        setContactComboBoxValues();
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
        //if(!isFormComplete()) return;
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
                        LocalDateTime.now(),
                        CurrentUser.getUserName(),
                        LocalDateTime.now(),
                        CurrentUser.getUserName(),
                        12,
                        12,
                        12,
                        "steve"
                );
                isSaved = dao.create(appointment);
                if (isSaved) {
                    AppointmentDataState.setSelectedAppointment(dao.findLast());
                    AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.VIEW);
                    fetchTableData();
                   // initializeForm();
                }
                break;
/*            case EDIT:
               appointment = new Appointment();
                isSaved = dao.update(appointment);
                if (isSaved) {
                    AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.VIEW);
                    fetchTableData();
                    //initializeForm();
                }
                break;*/
            default:
                return;
        }
    }


    private LocalDateTime getStartDateTime() {
        LocalDate startDatePart = startDate.getValue();
        LocalTime startTimePart = LocalTime.parse(startHour.getValue() + ":" + startMinute.getValue());
        return LocalDateTime.of(startDatePart,startTimePart);
    }

    private LocalDateTime getEndDateTime() {
        LocalDate endDatePart = endDate.getValue();
        LocalTime endTimePart = LocalTime.parse(endHour.getValue() + ":"+ endMinute.getValue());
        return LocalDateTime.of(endDatePart,endTimePart);
    }


/*
    */
/**
     * Method for initializing the Form data.
     * Switch method which calls specific methods appropriate to each FormType.
     * FormType is first checked, and then the appropriate methods are called depending on which type the current form is.
     *//*

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

*/


}
