package me.stevensheaves;

import javafx.application.Application;
import javafx.stage.Stage;
import me.stevensheaves.data.model.Country;
import me.stevensheaves.data.utils.LocationData;
import me.stevensheaves.view.controllers.utils.SceneChanger;

import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // TODO: 12/4/2020 remove the below comment 
       // Locale.setDefault(new Locale("fr"));
       // Locale.setDefault( new Locale.Builder().setLocale(Locale.CANADA).setLanguage("fr").build());
        SceneChanger.showPrimary(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
