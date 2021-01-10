package me.stevensheaves.view.controllers.reports.appointmentbycustomer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import me.stevensheaves.data.model.Appointment;
import me.stevensheaves.data.model.Customer;
import me.stevensheaves.database.utils.AppointmentDAO;
import me.stevensheaves.database.utils.CustomerDAO;

/**
 * Controller for the appointmentbycustomer.fxml view.
 * This Class is responsible for generating the "Appointment by Customer" report.
 */
public class AppointmentByCustomerController {
    @FXML
    private ScrollPane mainPane;

    /**
     * Initializes the view, and calls all necessary methods to build the report.
     */
    @FXML
    private void initialize() {
        VBox vBox = buildVBox();
        ObservableList<String> strings = buildSchedule(getAllCustomers());
        addSchedulesToVBox(strings, vBox);
        mainPane.setContent(vBox);
    }


    /**
     * Builds and returns a VBox with the appropriate configuration.
     * @return The configured VBox.
     */
    private VBox buildVBox() {
        VBox vBox = new VBox();
        Insets insets = new Insets(20,0,0,220);
        vBox.setSpacing(10);
        vBox.setPadding(insets);
        return vBox;
    }

    /**
     * Organizes the schedule data by Customer, then creates and formats an ObservableList of the report as Strings.
     * @param customerObservableList  The complete list of customers.
     * @return The complete list of Strings, when printed represents the Schedule.
     */
    private ObservableList<String> buildSchedule(ObservableList<Customer> customerObservableList) {
        ObservableList<String> strings = FXCollections.observableArrayList();
        for (Customer customer : customerObservableList) {
            String name = customer.getCustomerName();
            strings.add("Customer: " + name);
            ObservableList<Appointment> appointments = getAllAppointmentsForCustomer(customer.getCustomerId());
            buildListOfAppointments(appointments,strings);
        }

        return strings;
    }

    /**
     * Formats the lists of appointments neatly into a schedule..
     * @param appointments The list of appointments.
     * @param strings the list to add the appointments to.
     */
    private void buildListOfAppointments(ObservableList<Appointment> appointments, ObservableList<String> strings) {
        int counter = 0;
        int numberOfAppointments = appointments.size();
        if(numberOfAppointments > 0) {
            for(Appointment appointment: appointments) {
                strings.add(appointment.toScheduleFormatted());
                counter++;
                if (counter == numberOfAppointments) {
                    strings.add("______________________________________________________________________________________________________________________________");
                }
            }
        } else {
            strings.add("\t\t No Appointments Scheduled");
            strings.add("______________________________________________________________________________________________________________________________");
        }
    }

    /**
     * Adds the listOfAppointments to the VBox control, for displaying in the view.
     * @param listOfAppointments The ObservableList of appointments to be added to the VBox.
     * @param vBox The VBox which will be receiving the list of strings.
     */
    private void addSchedulesToVBox(ObservableList<String> listOfAppointments, VBox vBox) {
        for (String string : listOfAppointments) {
            Text textNode =  new Text(string);
            vBox.getChildren().add(textNode);
        }
    }

    /**
     * Utility method to get a list of all Customers.
     * @return Returns an ObservableList of all Customers in the database.
     */
    private ObservableList<Customer> getAllCustomers() {
        return new CustomerDAO().findAll();
    }

    /**
     * Generates an ObservableList of all appointments, for any one Customer.
     * @param id the Id of the customer, whose List of appointmetns is being created.
     * @return The ObservableList of appointments.
     */
    private ObservableList<Appointment> getAllAppointmentsForCustomer(int id) {
        return new AppointmentDAO().findByCustomerId(id);
    }

}
