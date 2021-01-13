package me.stevensheaves;

import javafx.application.Application;
import javafx.stage.Stage;
import me.stevensheaves.data.model.Country;
import me.stevensheaves.data.utils.LocationData;
import me.stevensheaves.view.controllers.utils.SceneChanger;

import java.util.Locale;


/**
 * The entry point of the application.
 */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        // Locale.setDefault(new Locale("fr"));
        SceneChanger.showPrimary(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
