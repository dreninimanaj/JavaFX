package com.example.knk_gr23.Controllers.Admin;

import com.example.knk_gr23.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ClientsComponentController{
    @FXML
    private Label clientIdLabel;
    @FXML
    private Label clientName;
    @FXML
    private Button toLoans_btn;
    @FXML
    private Label clientSurname;

    @FXML
    public void handleLoans(ActionEvent ae) {
    }

    public void setData(User user) {
        clientIdLabel.setText(String.valueOf(user.getId()));
        clientName.setText(user.getUsername());
        clientSurname.setText(user.getEmail());
    }
}
