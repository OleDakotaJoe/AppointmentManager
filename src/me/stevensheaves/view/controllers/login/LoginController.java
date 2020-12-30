package me.stevensheaves.view.controllers.login;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import me.stevensheaves.custom.controls.TextFieldLimited;
import me.stevensheaves.custom.utils.TimeUtilities;
import me.stevensheaves.data.model.Appointment;
import me.stevensheaves.data.model.CurrentUser;
import me.stevensheaves.data.model.Customer;
import me.stevensheaves.data.model.User;
import me.stevensheaves.data.utils.LocationData;
import me.stevensheaves.database.utils.AppointmentDAO;
import me.stevensheaves.database.utils.CustomerDAO;
import me.stevensheaves.database.utils.UserDAO;
import me.stevensheaves.view.controllers.state.AppointmentDataState;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LoginController {
    @FXML private TextFieldLimited userName;
    @FXML private PasswordField password;
    @FXML private Button loginButton;
    @FXML private Label warningLabel, locationLabel, mainHeading;
    private final String systemLanguage = LocationData.getSystemLanguage();
    private final String systemCountry = LocationData.getDisplayCountry();
    private String warningLabelText;



    // TODO: 12/4/2020 Determine location and change language.  
    // TODO: 12/4/2020 Display location in a label. 
    // TODO: 12/4/2020 launch or change next page.

    @FXML
    private void initialize() {
        changeLanguage();
        mainHeading.setFocusTraversable(true);
    }

    private void changeLanguage() {
        String label;
        String usernameText;
        String passwordText;
        String headingLabel;
        if(systemLanguage == "fr") {
            headingLabel= "Entrez les informations d'identification";
            label = "Connexion depuis: ";
            usernameText = "Nom d'utilisateur";
            passwordText = "Mot de passe";
            warningLabelText = "Votre nom d'utilisateur ou mot de passe était incorrect.";
        } else {
            headingLabel= "Please enter your username and password.";
            label = "Logging in from: ";
            usernameText = "Username";
            passwordText = "Password";
        }
        mainHeading.setText(headingLabel);
        locationLabel.setText(label + systemCountry);
        userName.setPromptText(usernameText);
        password.setPromptText(passwordText);
        warningLabelText ="Your username or password was incorrect.";
    }

    /**
     * Utility Method for validating user input, and changing the scene.
     */
    @FXML
    private void loginHandler() {
    UserDAO dao = new UserDAO();
    User currentUser = (userName.getText().matches("\\d")
            ? dao.validateUser(Integer.parseInt(userName.getText()), password.getText())
            : dao.validateUser(userName.getText(), password.getText()));
       if (currentUser != null)  {
           CurrentUser.login(currentUser.getId(), currentUser.getUserName(), true, LocalDateTime.now());
           ObservableList<Appointment> usersAppointments =  new AppointmentDAO().findByUserId(currentUser.getId());
           boolean hasAppointment = false;
           for (Appointment appointment : usersAppointments) {
               //checks all appointments for an overlap.
               if (new TimeUtilities().checkForOverlap(appointment.getStartDateTime(), appointment.getEndDateTime(), ZonedDateTime.now(ZoneId.systemDefault()), ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(15), false)) {
                   //if overlap is found, shows appointment overlap, then
                   showUpcomingAppointmentAlert(appointment);
                   hasAppointment = true;
                   break;
               }
           }
           if (!hasAppointment) showNoUpcomingAppointmentAlert();
           try {
               SceneChanger.changeScene(SceneNames.DASHBOARD);
               closeWindow();
           } catch (IOException e) {
               e.printStackTrace();
           }
       } else {
           warningLabel.setText(warningLabelText);
       }
    }


    private void showUpcomingAppointmentAlert(Appointment appointment) {
        Customer customer = new CustomerDAO().find(appointment.getCustomerId());
        String time = DateTimeFormatter.ofPattern("h:mm a z M/d/yy").format(appointment.getStartDateTime()) ;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Upcoming Appointment");
        alert.setHeaderText("You have an appointment coming up soon.");
        alert.setContentText("You have an upcoming appointment with " + customer.getCustomerName() + ", ID:  " + appointment.getCustomerId() + ". The appointment (ID: "+appointment.getAppointmentId()+") is scheduled" +
                "for " + time +".");
        alert.showAndWait();
    }

    private void showNoUpcomingAppointmentAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No upcoming appointments");
        alert.setHeaderText("You don't have any appointments within the next 15 minutes.");
        alert.showAndWait();
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) loginButton.getParent().getScene().getWindow();
        stage.close();
    }

}
