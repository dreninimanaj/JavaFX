package com.example.knk_gr23.App;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage){
        Navigator.navigate(stage, Navigator.ADMIN_PAGE);
    }
}
