package com.example.knk_gr23.Controllers.Client;

import com.example.knk_gr23.App.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {

    public Button home_btn;
    public Button apply_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button apply_btn1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    public void handleLogout(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @FXML
    private void handleApply(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.PAY_PAGE);
    }

    public void handleProfile(ActionEvent actionEvent) {
    }
}
