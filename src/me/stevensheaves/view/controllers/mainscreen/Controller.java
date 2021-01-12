package me.stevensheaves.view.controllers.mainscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import me.stevensheaves.view.controllers.utils.SceneChanger;
import me.stevensheaves.view.controllers.utils.SceneNames;

import java.io.IOException;

/**
 * Controller for the <code>mainscreen.fxml</code> view.
 */
public class Controller {

    /**
     * Points to <code>Button</code> element with <code>fx:id="loginButton"</code>.
     */
    @FXML
    private Button loginButton;
    /**
     * Points to <code>Button</code> element with <code>fx:id="createAccount"</code>.
     */
    @FXML
    private Button createAccount;

    /**
     * Utility method for changing scene based on which button is clicked.
     * Calls the <code>changeScene()</code> method on the <code>Main</code> class.
     * @param event
     * Passed into method by the button which calls it. Used to determine which button was pressed, and which scene to render.
     */
    @FXML
    public void changeScene(ActionEvent event) {
        SceneNames sceneName = SceneNames.MAIN;
        if (event.getSource().equals(loginButton)){
            sceneName = SceneNames.LOGIN;
        } else if (event.getSource().equals(createAccount)) {
            sceneName = SceneNames.ADD_USER;
        }
        try {
            SceneChanger.changeScene(sceneName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
