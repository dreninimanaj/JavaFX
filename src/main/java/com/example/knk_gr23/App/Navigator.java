package com.example.knk_gr23.App;

import com.example.knk_gr23.Controllers.Client.TableController;
import com.example.knk_gr23.Models.dto.PaymentDto;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class Navigator {
    public final static String LOGIN_PAGE = "/Fxml/Login.fxml";
    public final static String HOME_PAGE = "/Fxml/Client/ClientH.fxml";
    public final static String ADMIN_PAGE = "/Fxml/Admin/AdminClients.fxml";
    public final static String PAY_PAGE = "/Fxml/Client/PayDebt.fxml";
    public final static String TABLE_PAGE = "/Fxml/Client/TableLoans.fxml";
    public final static String ADD_LOAN_PAGE = "/Fxml/Client/AddLoan.fxml";
    public final static String CLIENT_PROFILE_PAGE = "/Fxml/Client/ClientProfile.fxml";
    public final static String PROFILE_ADMIN = "/Fxml/Admin/ProfileAdmin.fxml";


    public final static String REQUESTS_PAGE = "/Fxml/Admin/Requests.fxml";
    public final static String CALCULATOR_PAGE = "/Fxml/Client/Calculator.fxml";
    public final static String SIGN_UP_PAGE = "/Fxml/Admin/SignUp.fxml";
    // Method to navigate using an event
    public static void navigate(Event event, String form){
        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        stage.setResizable(false);
        navigate(stage, form);
    }


    public static void navigate(Event event, String form, Object controller) {
        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        navigate(stage, form, controller);
    }

    // Basic navigation method
    public static void navigate(Stage stage, String form){
        Pane formPane = loadPane(form);
        Scene newScene = new Scene(formPane);
        stage.setScene(newScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void navigate(Pane pane, String form){
        Pane formPane = loadPane(form);
        pane.getChildren().clear();
        pane.getChildren().add(formPane);
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
            stage.setResizable(false);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void navigate(BorderPane borderPane, String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(fxmlPath));
        try {
            Node node = loader.load();
            borderPane.setCenter(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    private void changeLanguage(){
//        Locale locale = Locale.of("sq");
//        Locale.setDefault(locale);
//    }
    private static Pane loadPane(String form){

        ResourceBundle bundle = ResourceBundle.getBundle(
                "translations.content", Locale.getDefault()
        );
        FXMLLoader loader = new FXMLLoader(
                Navigator.class.getResource(form), bundle
        );
        try {
            return loader.load();
        }catch (IOException ioe){
            return null;
        }
    }
}
