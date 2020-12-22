package me.stevensheaves;

import javafx.application.Application;
import javafx.stage.Stage;
import me.stevensheaves.view.controllers.utils.SceneChanger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // TODO: 12/4/2020 remove the below comment 
        //Locale.setDefault(new Locale("fr"));
        SceneChanger.showPrimary(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
