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
import me.stevensheaves.data.utils.ActivityLogger;
import me.stevensheaves.data.utils.LocationData;
import me.stevensheaves.database.utils.AppointmentDAO;
import me.stevensheaves.database.utils.CustomerDAO;
import me.stevensheaves.database.utils.UserDAO;
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


    /**
     * Initializes the view by calling the change language function.
     * Also, sets, the mainHeading's FocusTraversable property to true. This is so that the TextFields don't automatically get the focus, which obscures the prompt-text.
     */
    @FXML
    private void initialize() {
        changeLanguage();
        mainHeading.setFocusTraversable(true);
    }

    /**
     * Sets appropriate Text Controls in the view to either French or English, depending on the SystemCountry.
     */
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
            warningLabelText = "Your username or password was incorrect.";
        }
        mainHeading.setText(headingLabel);
        locationLabel.setText(label + systemCountry);
        userName.setPromptText(usernameText);
        password.setPromptText(passwordText);
    }

    /**
     * Utility Method for validating user input, and changing the scene.
     */
    @FXML
    private void loginHandler() {
        User currentUser = validateAndReturnUser();
        writeLoginActivityToFile(currentUser, userName.getText());
        if (currentUser != null)  {
           setUserLoggedIn(currentUser);
           checkForUpcomingAppointments(currentUser);
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

    /**
     * Validates and returns the User object of the validated user.
     * Calls the validateUser function from the UserDAO class, and returns the user, as appropriate.
     * userName can accept either a String or the User's ID
     * @return tje Validated user, if valid, or null if not.
     */
    private User validateAndReturnUser() {
        UserDAO dao = new UserDAO();
        User currentUser = (userName.getText().matches("\\d")
                ? dao.validateUser(Integer.parseInt(userName.getText()), password.getText())
                : dao.validateUser(userName.getText(), password.getText()));
        return currentUser;
    }

    /**
     * Sets the the Calls the CurrentUser.login() method on the User object passed in.
     * @param currentUser The User object to be logged in.
     */
    private void setUserLoggedIn(User currentUser) {
        CurrentUser.login(currentUser.getId(), currentUser.getUserName(), true, LocalDateTime.now());
    }

    /**
     * Checks to see if User has any appointments coming up within fifteen minutes, and displays appropriate alerts.
     * @param currentUser The User whose appointments must be checked for.
     */
    private void checkForUpcomingAppointments(User currentUser) {
        ObservableList<Appointment> usersAppointments =  new AppointmentDAO().findByUserId(currentUser.getId());
        boolean hasAppointment = false;
        for (Appointment appointment : usersAppointments) {
            //checks all appointments for an overlap.
            if (new TimeUtilities().checkForOverlap(appointment.getStartDateTime(),
                    appointment.getEndDateTime(),
                    ZonedDateTime.now(ZoneId.systemDefault()),
                    ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(15),
                    false
            )) {
                //if overlap is found, shows appointment overlap, then
                showUpcomingAppointmentAlert(appointment);
                hasAppointment = true;
                break;
            }
        }
        if (!hasAppointment) showNoUpcomingAppointmentAlert();
    }

    /**
     * Shows an Alert with details about an upcoming appointment.
     * @param appointment
     */
    private void showUpcomingAppointmentAlert(Appointment appointment) {
        Customer customer = new CustomerDAO().find(appointment.getCustomerId());
        String time = DateTimeFormatter.ofPattern("h:mm a z M/d/yy").format(appointment.getStartDateTime()) ;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Upcoming Appointment");
        alert.setHeaderText("You have an appointment coming up soon.");
        alert.setContentText("You have an upcoming appointment with " + customer.getCustomerName() + ", ID:  " + appointment.getCustomerId() + ". The appointment (ID: "+appointment.getAppointmentId()+") is scheduled" +
                "for " + time +".");
        alert.showAndWait();
    }

    /**
     * Alerts that there are no upcoming appointments.
     */
    private void showNoUpcomingAppointmentAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No upcoming appointments");
        alert.setHeaderText("You don't have any appointments within the next 15 minutes.");
        alert.showAndWait();
    }

    /**
     * Utility method to write all login activity to a file.
     * @param user The user that attempted to login.
     * @param attemptedUsername The username that the user attempted to login using.
     */
    private void writeLoginActivityToFile(User user, String attemptedUsername) {
        boolean wasSuccessful = user != null;
        ActivityLogger.getInstance().writeActivityToLog(attemptedUsername , wasSuccessful);
    }

    /**
     * Closes the login window without closing the application.
     */
    @FXML
    private void closeWindow() {
        Stage stage = (Stage) loginButton.getParent().getScene().getWindow();
        stage.close();
    }

}
