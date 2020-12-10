package me.stevensheaves.view.controllers.appointment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;

public class AppointmentController {
    @FXML private Button appointmentsButton, contactsButton, reportsButton, customersButton, dashboardButton, addAppointmentButton;
    @FXML private BorderPane appointmentPane;

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
            SceneChanger.addChildScene(SceneNames.APPOINTMENT_FORM, appointmentPane);
        }
    }

}
