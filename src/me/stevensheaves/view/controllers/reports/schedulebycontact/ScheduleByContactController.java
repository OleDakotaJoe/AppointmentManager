package me.stevensheaves.view.controllers.reports.schedulebycontact;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import me.stevensheaves.data.model.Appointment;
import me.stevensheaves.data.model.Contact;
import me.stevensheaves.database.utils.AppointmentDAO;
import me.stevensheaves.database.utils.ContactDAO;


/**
 * Controller for the appointmentsbycontact.fxml view.
 * This Class is responsible for generating the "Appointment by Contact" report.
 */
public class ScheduleByContactController {
    @FXML private ScrollPane mainPane;

    /**
     * Initializes the view, and calls all necessary methods to build the report.
     */
    @FXML
    private void initialize() {
        VBox vBox = buildVBox();
        ObservableList<String> strings = buildSchedule(getAllContacts());
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
     * Organizes the schedule data by Contact, then creates and formats an ObservableList of the report as Strings.
     * @param contactObservableList  The complete list of contacts.
     * @return The complete list of Strings, when printed represents the Schedule.
     */
    private ObservableList<String> buildSchedule(ObservableList<Contact> contactObservableList) {
        ObservableList<String> strings = FXCollections.observableArrayList();
        for (Contact contact : contactObservableList) {
            String name = contact.getName();
            strings.add("Contact: " + name);
            ObservableList<Appointment> appointments = getAllAppointmentsForContact(contact.getId());
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
     * Utility method to get a list of all Contacts.
     * @return Returns an ObservableList of all Contacts in the database.
     */
    private ObservableList<Contact> getAllContacts() {
         return new ContactDAO().findAll();
    }


    /**
     * Generates an ObservableList of all appointments, for any one Contact.
     * @param contactId the Id of the contact, whose List of appointmetns is being created.
     * @return The ObservableList of appointments.
     */
    private ObservableList<Appointment> getAllAppointmentsForContact(int contactId) {
        return new AppointmentDAO().findByContactId(contactId);
    }

}
