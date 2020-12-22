package me.stevensheaves.view.controllers.utils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import me.stevensheaves.data.utils.LocationData;

import java.io.IOException;

public class SceneChanger {
    private static Stage primaryStage;
    private final static String BASE_PATH= "/me/stevensheaves/view/controllers/";
    private static final String systemLanguage = LocationData.getSystemLanguage();
    // TODO: 12/5/2020 implement "You're logging in from: sysCountry"
    private static final String systemCountry = LocationData.getSystemCountry();

    public static void showPrimary(Stage primaryStage) throws IOException {
        // TODO: 12/19/2020 Change back to loading from login screen 
        SceneChanger.primaryStage = primaryStage;
//      Parent root = FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH +  "mainscreen/mainscreen.fxml"));
        Parent root = FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH +  "dashboard/dashboard.fxml"));

        primaryStage.setTitle("Appointment Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void changeScene(SceneNames sceneNames) throws IOException {
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

}
