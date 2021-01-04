package me.stevensheaves.view.controllers.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import me.stevensheaves.data.utils.LocationData;
import me.stevensheaves.data.model.Report;

import java.io.IOException;

public class SceneChanger {
    private static Stage primaryStage;
    private final static String BASE_PATH= "/me/stevensheaves/view/controllers/";
    private static final String systemLanguage = LocationData.getSystemLanguage();

    public static void showPrimary(Stage primaryStage) throws IOException {
        SceneChanger.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH +  "mainscreen/mainscreen.fxml"));
        primaryStage.setTitle("Appointment Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static Parent changeScene(SceneNames sceneNames) throws IOException {
        Parent pane = null;
        switch (sceneNames) {
            case LOGIN:
                String title;
                if(systemLanguage == "fr") {
                    title = "Connectez-vous à votre compte";
                } else {
                    title = "Login to Your Account";
                }
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "login/login.fxml"));
                stage.setTitle(title);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.show();
                break;
            case DASHBOARD:
                pane = FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "dashboard/dashboard.fxml"));
                break;
            case CUSTOMERS:
                pane = FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "customers/customers.fxml"));
                break;
            case CONTACTS:
                pane = FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "contacts/contacts.fxml"));
                break;
            case REPORTS:
                pane = FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "reports/reports.fxml"));
                break;
            case APPOINTMENT:
                pane = FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "appointment/appointment.fxml"));
                break;
            default:
                pane = FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "mainscreen/mainscreen.fxml"));
                break;
        }

        if (pane != null) primaryStage.setScene(new Scene(pane));
        return pane;
    }

    public static void addChildScene(SceneNames sceneName, Pane currentPane) throws IOException {
        switch(sceneName) {
            case APPOINTMENT_FORM:
                ((BorderPane) currentPane).setCenter(FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "appointment/appointmentform.fxml")));
                break;
            case CONTACTS_FORM:
                ((BorderPane) currentPane).setCenter(FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "contacts/contactsform.fxml")));
                break;
            case CUSTOMER_FORM:
                ((BorderPane) currentPane).setCenter(FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "customers/customersform.fxml")));
                break;
            default:
                break;

        }
    }

    public static void addChildScene(Report.ReportType reportType, Pane currentPane) throws IOException {
        switch(reportType) {
            case APPOINTMENT_BY_CUSTOMER:
                ((BorderPane) currentPane).setCenter(FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "reports/appointmentbycustomer/appointmentbycustomer.fxml")));
                break;
            case APPOINTMENT_BY_TYPE:
                ((BorderPane) currentPane).setCenter(FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "reports/appointmentbytypemonth/appointmentbytype.fxml")));
                break;
            case SCHEDULE_BY_CONTACT:
                ((BorderPane) currentPane).setCenter(FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH + "reports/schedulebycontact/schedulebycontact.fxml")));
                break;
            default:
                break;
        }
    }


}
