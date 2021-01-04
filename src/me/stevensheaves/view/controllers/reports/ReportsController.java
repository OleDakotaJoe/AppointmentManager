package me.stevensheaves.view.controllers.reports;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import me.stevensheaves.data.model.Report;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;

public class ReportsController {
    @FXML private BorderPane mainPane;
    @FXML private Button appointmentsButton;
    @FXML private Button contactsButton;
    @FXML private Button reportsButton;
    @FXML private Button customersButton;
    @FXML private Button dashboardButton;
    @FXML private ComboBox<Report> reportTypes;


    @FXML
    private void initialize() {
        setReportTypes();
    }

    @FXML
    private void setReportTypes() {
        ObservableList<Report> reports =FXCollections.observableArrayList();
        reports.add(new Report("Appointment By Type/Month", Report.ReportType.APPOINTMENT_BY_TYPE));
        reports.add(new Report("Appointment By Customer", Report.ReportType.APPOINTMENT_BY_CUSTOMER));
        reports.add(new Report("Schedule by Contact", Report.ReportType.SCHEDULE_BY_CONTACT));
        reportTypes.setItems(reports);
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
    private void runReport() {
        try {
            SceneChanger.addChildScene(reportTypes.getValue().getReportType(), mainPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Need Some help?");
        alert.setHeaderText("We've got your back. ");
        alert.setContentText("\n\n\t\t\t\tTo Run a Report\n Select the appropriate report from the dropdown menu to the far left, then click \"Run Report\" and your report will automatically display in the blank space on the window.");
        alert.show();
    }
}
