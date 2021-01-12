package me.stevensheaves.view.controllers.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import me.stevensheaves.data.utils.LocationData;
import me.stevensheaves.data.utils.Report;

import java.io.IOException;

/**
 * This utility class is used for handling all scene changes.
 */
public class SceneChanger {
    private static Stage primaryStage;
    private final static String BASE_PATH= "/me/stevensheaves/view/controllers/";
    private static final String systemLanguage = LocationData.getSystemLanguage();

    /**
     * Loads the primary stage.
     * @param primaryStage This stage is passed in by the Main method which calls it by default.
     * @throws IOException Throws an IOException if there was an exception when loading the fxml file.
     */
    public static void showPrimary(Stage primaryStage) throws IOException {
        SceneChanger.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(SceneChanger.class.getResource(BASE_PATH +  "mainscreen/mainscreen.fxml"));
        primaryStage.setTitle("Appointment Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Handles the changing of scenes.
     * Uses a switch method and the <code>SceneNames</code> enum as its' switch argument.
     * @param sceneName The name of the scene to be added: this enum is designed to allow cleaner code by using a switch statement, instead of multiple <code>if</code> statements.
     * @return Returns the <code>Parent</code> of the scene it is creating, so that further processing may be done on the it, when necessary.
     * @throws IOException Throws an IOException if there was an exception when loading the fxml file.
     */
    public static Parent changeScene(SceneNames sceneName) throws IOException {
        Parent pane = null;
        switch (sceneName) {
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

    /**
     * Adds a child scene to the Pane that is passed to it.
     * @param sceneName The name of the scene to be added: this enum is designed to allow cleaner code by using a switch statement, instead of multiple <code>if</code> statements.
     * @param currentPane The Pane to add the Scene to.
     * @throws IOException Throws an IOException if there was an exception when loading the fxml file.
     */
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

    /**
     * Adds a child scene to the Pane that is passed to it.
     * @param reportType The name of the report to be added: this enum is designed to allow cleaner code by using a switch statement, instead of multiple <code>if</code> statements.
     * @param currentPane The Pane to add the Scene to.
     * @throws IOException Throws an IOException if there was an exception when loading the fxml file.
     */
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
