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


public class ScheduleByContactController {
    @FXML private ScrollPane mainPane;

    @FXML
    private void initialize() {
        VBox vBox = buildVBox();
        ObservableList<String> strings = buildSchedule(getAllContacts());
        addSchedulesToVBox(strings, vBox);
        mainPane.setContent(vBox);
    }


    private VBox buildVBox() {
        VBox vBox = new VBox();
        Insets insets = new Insets(20,0,0,220);
        vBox.setSpacing(10);
        vBox.setPadding(insets);
        return vBox;
    }

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

    private void addSchedulesToVBox(ObservableList<String> listOfAppointments, VBox vBox) {
        for (String string : listOfAppointments) {
            Text textNode =  new Text(string);
            vBox.getChildren().add(textNode);
        }
    }

    private ObservableList<Contact> getAllContacts() {
         return new ContactDAO().findAll();
    }

    private ObservableList<Appointment> getAllAppointmentsForContact(int contactId) {
        return new AppointmentDAO().findByContactId(contactId);
    }

}
