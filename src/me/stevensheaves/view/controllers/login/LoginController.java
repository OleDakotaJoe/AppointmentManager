package me.stevensheaves.view.controllers.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import me.stevensheaves.custom.controls.TextFieldLimited;
import me.stevensheaves.data.model.CurrentUser;
import me.stevensheaves.data.model.User;
import me.stevensheaves.database.utils.UserDAO;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;
import java.time.LocalDateTime;

public class LoginController {


@FXML private TextFieldLimited userName;
@FXML private PasswordField password;
@FXML private Button loginButton;
@FXML private Label warningLabel;

    // TODO: 12/4/2020 Determine location and change language.  
    // TODO: 12/4/2020 Display location in a label. 
    // TODO: 12/4/2020 launch or change next page.

    /**
     * Utility Method for validating user input, and changing the scene.
     */
    @FXML
    public void loginHandler() {
    UserDAO dao = new UserDAO();
    User currentUser = (userName.getText().matches("\\d")
            ? dao.validateUser(Integer.parseInt(userName.getText()), password.getText())
            : dao.validateUser(userName.getText(), password.getText()));
       if (currentUser != null)  {
           CurrentUser.login(currentUser.getId(), currentUser.getUserName(), true, LocalDateTime.now());
           try {
               SceneChanger.changeScene(SceneNames.DASHBOARD);
               closeWindow();
           } catch (IOException e) {
               e.printStackTrace();
           }
       } else {
           warningLabel.setText("Your username or password was incorrect.");
       }
    }


    @FXML
    public void closeWindow() {
        Stage stage = (Stage) loginButton.getParent().getScene().getWindow();
        stage.close();
    }

}
