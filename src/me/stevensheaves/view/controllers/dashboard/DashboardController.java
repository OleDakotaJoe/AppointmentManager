package me.stevensheaves.view.controllers.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import me.stevensheaves.custom.utils.DateTimeCellFormatter;
import me.stevensheaves.data.model.Appointment;
import me.stevensheaves.data.model.Contact;
import me.stevensheaves.data.model.Customer;
import me.stevensheaves.database.utils.AppointmentDAO;
import me.stevensheaves.database.utils.ContactDAO;
import me.stevensheaves.database.utils.CustomerDAO;
import me.stevensheaves.view.controllers.state.AppointmentDataState;
import me.stevensheaves.view.controllers.state.ContactDataState;
import me.stevensheaves.view.controllers.state.CustomerDataState;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;
import java.time.ZonedDateTime;

public class DashboardController {
    @FXML private Button appointmentsButton,
            contactsButton,
            customersButton,
            reportsButton,
            dashboardButton,
            addAppointmentButtonAll,
            editAppointmentButtonAll,
            viewAppointmentButtonAll,
            addAppointmentButtonToday,
            editAppointmentButtonToday,
            viewAppointmentButtonToday,
            addAppointmentButtonWeek,
            editAppointmentButtonWeek,
            viewAppointmentButtonWeek,
            addAppointmentButtonMonth,
            editAppointmentButtonMonth,
            viewAppointmentButtonMonth,
            addContact,
            editContact,
            viewContact,
            addCustomer,
            editCustomer,
            viewCustomer;

    @FXML private TableView<Customer> customersTable;
    @FXML private TableColumn<Customer, Integer> customerId;
    @FXML private TableColumn<Customer, String> customerName;
    @FXML private TableColumn<Customer, String> customerAddress;
    @FXML private TableColumn<Customer, String> postalCode;
    @FXML private TableColumn<Customer, String> phoneNumber;
    @FXML private TableColumn<Customer, String> divisionName;

    @FXML private TableView<Contact> contactsTable;
    @FXML private TableColumn<Contact,Integer> contactId;
    @FXML private TableColumn<Contact,String> contactName;
    @FXML private TableColumn<Contact,String> email;

    @FXML private TableView<Appointment> allAppointments;
    @FXML private TableColumn<Appointment, String> allAppointmentsTitle, allAppointmentsDescription, allAppointmentsLocation, allAppointmentsContact, allAppointmentsType;
    @FXML private TableColumn<Appointment, ZonedDateTime> allAppointmentsStartTime, allAppointmentsEndTime;
    @FXML private TableColumn<Appointment, Integer> allAppointmentId, allAppointmentsCustomerId;

    @FXML private TableView<Appointment> todayAppointments;
    @FXML private TableColumn<Appointment, String> todayAppointmentsTitle, todayAppointmentsDescription, todayAppointmentsLocation, todayAppointmentsContact, todayAppointmentsType;
    @FXML private TableColumn<Appointment, ZonedDateTime> todayAppointmentsStartTime, todayAppointmentsEndTime;
    @FXML private TableColumn<Appointment, Integer> todayAppointmentId, todayAppointmentsCustomerId;

    @FXML private TableView<Appointment> weekAppointments;
    @FXML private TableColumn<Appointment, String> weekAppointmentsTitle, weekAppointmentsDescription, weekAppointmentsLocation, weekAppointmentsContact, weekAppointmentsType;
    @FXML private TableColumn<Appointment, ZonedDateTime> weekAppointmentsStartTime, weekAppointmentsEndTime;
    @FXML private TableColumn<Appointment, Integer> weekAppointmentId, weekAppointmentsCustomerId;

    @FXML private TableView<Appointment> monthAppointments;
    @FXML private TableColumn<Appointment, String> monthAppointmentsTitle, monthAppointmentsDescription, monthAppointmentsLocation, monthAppointmentsContact, monthAppointmentsType;
    @FXML private TableColumn<Appointment, ZonedDateTime> monthAppointmentsStartTime, monthAppointmentsEndTime;
    @FXML private TableColumn<Appointment, Integer> monthAppointmentId, monthAppointmentsCustomerId;





    @FXML
    private void initialize() {
        fetchDataForAllTables();
        setTableData();
    }

    private void fetchDataForAllTables() {
        fetchCustomerData();
        fetchContactData();
        fetchAppointmentData();
    }
    private void fetchCustomerData() {
        CustomerDAO dao = new CustomerDAO();
        CustomerDataState.setAllCustomers(dao.findAll());
    }
    private void fetchContactData() {
        ContactDAO dao = new ContactDAO();
        ContactDataState.setAllContacts(dao.findAll());
    }
    private void fetchAppointmentData() {
        AppointmentDAO dao = new AppointmentDAO();
        AppointmentDataState.setAllAppointments(dao.findAll());
    }

    private void setTableData() {
        setCustomersTable();
        setContactsTable();
        setAppointmentsAllTable();
        setAppointmentsTodayTable();
        setAppointmentsWeekTable();
        setAppointmentsMonthTable();
    }

    private void setCustomersTable() {
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        divisionName.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        customersTable.setItems(CustomerDataState.getAllCustomers());
    }

    private void setContactsTable() {
        contactId.setCellValueFactory(new PropertyValueFactory<>("id"));
        contactName.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        contactsTable.setItems(ContactDataState.getAllContacts());
    }

    private void setAppointmentsAllTable () {
        allAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        allAppointmentsTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        allAppointmentsDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        allAppointmentsLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        allAppointmentsContact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        allAppointmentsType.setCellValueFactory(new PropertyValueFactory<>("type"));
        allAppointmentsStartTime.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        allAppointmentsStartTime.setCellFactory(new DateTimeCellFormatter<>("h:mm a z M/d/yy"));
        allAppointmentsEndTime.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        allAppointmentsEndTime.setCellFactory(new DateTimeCellFormatter<>("h:mm a z M/d/yy"));
        allAppointmentsCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        allAppointments.setItems(AppointmentDataState.getAllAppointments());
    }

    private void setAppointmentsTodayTable() {
        todayAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        todayAppointmentsTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        todayAppointmentsDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        todayAppointmentsLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        todayAppointmentsContact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        todayAppointmentsType.setCellValueFactory(new PropertyValueFactory<>("type"));
        todayAppointmentsStartTime.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        todayAppointmentsStartTime.setCellFactory(new DateTimeCellFormatter<>("h:mm a z M/d/yy"));
        todayAppointmentsEndTime.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        todayAppointmentsEndTime.setCellFactory(new DateTimeCellFormatter<>("h:mm a z M/d/yy"));
        todayAppointmentsCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        todayAppointments.setItems(AppointmentDataState.getTodayAppointments());
    }

    private void setAppointmentsWeekTable() {
        weekAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        weekAppointmentsTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        weekAppointmentsDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        weekAppointmentsLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        weekAppointmentsContact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        weekAppointmentsType.setCellValueFactory(new PropertyValueFactory<>("type"));
        weekAppointmentsStartTime.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        weekAppointmentsStartTime.setCellFactory(new DateTimeCellFormatter<>("h:mm a z M/d/yy"));
        weekAppointmentsEndTime.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        weekAppointmentsEndTime.setCellFactory(new DateTimeCellFormatter<>("h:mm a z M/d/yy"));
        weekAppointmentsCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        weekAppointments.setItems(AppointmentDataState.getThisWeekAppointments());
    }

    private void setAppointmentsMonthTable() {
        monthAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        monthAppointmentsTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthAppointmentsDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthAppointmentsLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthAppointmentsContact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        monthAppointmentsType.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthAppointmentsStartTime.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        monthAppointmentsStartTime.setCellFactory(new DateTimeCellFormatter<>("h:mm a z M/d/yy"));
        monthAppointmentsEndTime.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        monthAppointmentsEndTime.setCellFactory(new DateTimeCellFormatter<>("h:mm a z M/d/yy"));
        monthAppointmentsCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        monthAppointments.setItems(AppointmentDataState.getThisMonthAppointments());
    }

    @FXML
    private void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Need Some help?");
        alert.setHeaderText("We've got your back. ");
        alert.setContentText("\n\n\t\t\t\tTo Add An Entry\n Click \"Add\" underneath the table in which it was located. Afterwards, you will be redirected to the appropriate page to make your addition. " +
                "\n\n\t\t\t\tTo Edit an Entry\nFirst click on the entry you would like to edit, then click \"Edit\" underneath the table in which it was located. Afterwards, you will be redirected to the appropriate page to make your changes." +
                "\n\n\t\t\t\tTo View an Entry\nFirst click on the entry you would like to view, then click \"View\"underneath the table in which it was located. Afterwards, you will be redirected to the appropriate page to make your changes.");
        alert.show();
    }

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
    }

    @FXML
    private void handleEditAppointmentAll(ActionEvent event) throws IOException {
        if(event.getSource().equals(addAppointmentButtonAll)) {
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.ADD);
            changeToAppointmentScene(allAppointments);
        }
        if(event.getSource().equals(editAppointmentButtonAll)) {
            if(!isValidSelection(allAppointments)) return;
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.EDIT);
            changeToAppointmentScene(allAppointments);
        }
        if(event.getSource().equals(viewAppointmentButtonAll)) {
            if(!isValidSelection(allAppointments)) return;
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.VIEW);
            changeToAppointmentScene(allAppointments);
        }
    }
    @FXML
    private void handleEditAppointmentToday(ActionEvent event) throws IOException {
        if(event.getSource().equals(addAppointmentButtonToday)) {
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.ADD);
            changeToAppointmentScene(todayAppointments);
        }
        if(event.getSource().equals(editAppointmentButtonToday)) {
            if(!isValidSelection(todayAppointments)) return;
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.EDIT);
            changeToAppointmentScene(todayAppointments);
        }
        if(event.getSource().equals(viewAppointmentButtonToday)) {
            if(!isValidSelection(todayAppointments)) return;
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.VIEW);
            changeToAppointmentScene(todayAppointments);
        }
    }

    @FXML
    private void handleEditAppointmentWeek(ActionEvent event) throws IOException {
        if(event.getSource().equals(addAppointmentButtonWeek)) {
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.ADD);
            changeToAppointmentScene(weekAppointments);
        }
        if(event.getSource().equals(editAppointmentButtonWeek)) {
            if(!isValidSelection(weekAppointments)) return;
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.EDIT);
            changeToAppointmentScene(weekAppointments);
        }
        if(event.getSource().equals(viewAppointmentButtonWeek)) {
            if(!isValidSelection(weekAppointments)) return;
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.VIEW);
            changeToAppointmentScene(weekAppointments);
        }
    }

    @FXML
    private void handleEditAppointmentMonth(ActionEvent event) throws IOException {
        if(event.getSource().equals(addAppointmentButtonMonth)) {
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.ADD);
            changeToAppointmentScene(monthAppointments);
        }
        if(event.getSource().equals(editAppointmentButtonMonth)) {
            if(!isValidSelection(monthAppointments)) return;
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.EDIT);
            changeToAppointmentScene(monthAppointments);
        }
        if(event.getSource().equals(viewAppointmentButtonMonth)) {
            if(!isValidSelection(monthAppointments)) return;
            AppointmentDataState.setCurrentFormType(AppointmentDataState.FormType.VIEW);
            changeToAppointmentScene(monthAppointments);
        }
    }
    @FXML
    private void handleContactCRUD(ActionEvent event) throws IOException {
        if(event.getSource().equals(addContact)) {
            ContactDataState.setCurrentFormType(ContactDataState.FormType.ADD);
            changeToContactScene();
        }
        if(event.getSource().equals(editContact)) {
            if(!isValidSelection(contactsTable)) return;
            ContactDataState.setCurrentFormType(ContactDataState.FormType.EDIT);
            changeToContactScene();
        }
        if(event.getSource().equals(viewContact)) {
            if(!isValidSelection(contactsTable)) return;
            ContactDataState.setCurrentFormType(ContactDataState.FormType.VIEW);
            changeToContactScene();
        }
    }

    @FXML
    private void handleCustomerCRUD(ActionEvent event) throws IOException {
        if(event.getSource().equals(addCustomer)) {
            CustomerDataState.setCurrentFormType(CustomerDataState.FormType.ADD);
            changeToCustomerScene();
        }
        if(event.getSource().equals(editCustomer)) {
            if(!isValidSelection(customersTable)) return;
            CustomerDataState.setCurrentFormType(CustomerDataState.FormType.EDIT);
            changeToCustomerScene();
        }
        if(event.getSource().equals(viewCustomer)) {
            if(!isValidSelection(customersTable)) return;
            CustomerDataState.setCurrentFormType(CustomerDataState.FormType.VIEW);
            changeToCustomerScene();
        }
    }

    private boolean isValidSelection(TableView table) {
        int index = table.getSelectionModel().getSelectedIndex();
        if(index < 0) {
            showNothingSelectedAlert();
            return false;
        }
        return true;
    }

    private void changeToAppointmentScene(TableView<Appointment> table) throws IOException {
        AppointmentDataState.setSelectedAppointment(table.getSelectionModel().getSelectedItem());
        Pane pane = (Pane) SceneChanger.changeScene(SceneNames.APPOINTMENT);
        SceneChanger.addChildScene(SceneNames.APPOINTMENT_FORM, pane);
    }

    private void changeToCustomerScene() throws IOException {
        CustomerDataState.setSelectedCustomer(customersTable.getSelectionModel().getSelectedItem());
        Pane pane = (Pane) SceneChanger.changeScene(SceneNames.CUSTOMERS);
        SceneChanger.addChildScene(SceneNames.CUSTOMER_FORM, pane);
    }

    private void changeToContactScene() throws IOException {
        ContactDataState.setSelectedContact(contactsTable.getSelectionModel().getSelectedItem());
        Pane pane = (Pane) SceneChanger.changeScene(SceneNames.CONTACTS);
        SceneChanger.addChildScene(SceneNames.CONTACTS_FORM, pane);
    }

    /**
     * Shows an Alert dialog that informs the user that no contact is selected.
     */
    private void showNothingSelectedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Nothing selected");
        alert.setHeaderText("You didn't select anything");
        alert.setContentText("Before you can complete this action, you must first select an item. Try clicking on " +
                "an item before proceeding.");
        alert.show();
    }
}
