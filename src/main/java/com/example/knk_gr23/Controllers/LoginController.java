package com.example.knk_gr23.Controllers;

import com.example.knk_gr23.App.Navigator;
import com.example.knk_gr23.Controllers.Client.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public Label adresa_perdoruesit;
    @FXML
    public Button login_btn;
    @FXML
    public PasswordField password_hapsira;
    @FXML
    public ChoiceBox acc_selector;
    @FXML
    public Label error_lbl;
    @FXML
    public TextField textfield_perdoruesit;

    @FXML
    private void handleLogin(ActionEvent ae) {
        ClientController clientController = new ClientController(); // Assuming ClientController is needed for HOME_PAGE
        Navigator.navigate(ae, Navigator.HOME_PAGE, clientController);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        login_btn.setOnAction(event -> {
//            ClientController clientController = new ClientController();
//            Navigator.navigate(event, Navigator.HOME_PAGE, clientController);
//        });
    }
}
