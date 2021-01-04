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

public class AppointmentByCustomerController {
    @FXML
    private ScrollPane mainPane;

    @FXML
    private void initialize() {
        VBox vBox = buildVBox();
        ObservableList<String> strings = buildSchedule(getAllCustomers());
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

    private ObservableList<Customer> getAllCustomers() {
        return new CustomerDAO().findAll();
    }

    private ObservableList<Appointment> getAllAppointmentsForCustomer(int id) {
        return new AppointmentDAO().findByCustomerId(id);
    }

}
