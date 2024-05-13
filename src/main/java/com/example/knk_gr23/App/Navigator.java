package com.example.knk_gr23.App;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    public final static String LOGIN_PAGE = "/Fxml/Login.fxml";
    public final static String HOME_PAGE = "/Fxml/Client/Client.fxml";
    //public final static String CREATE_ACCOUNT_PAGE = "create_user_form.fxml";

    // Method to navigate using an event
    public static void navigate(Event event, String form){
        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        navigate(stage, form);
    }

    public static void navigate(Event event, String form, Object controller) {
        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        navigate(stage, form, controller);
    }

    // Basic navigation method
    public static void navigate(Stage stage, String form){
        navigate(stage, form, null);
    }

    // Overloaded method to set a controller explicitly
    public static void navigate(Stage stage, String form, Object controller){
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(form));
        if (controller != null) {
            loader.setController(controller);
        }
        try {
            Scene newScene = new Scene(loader.load());
            stage.setScene(newScene);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace(); // Better error handling or logging here
        }
    }
}
