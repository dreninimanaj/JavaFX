package com.example.knk_gr23.Controllers.Admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {

    @FXML
    private VBox clientsList;

    @FXML
    private Label clientIdLabel;
    @FXML
    private Label clientName;
    @FXML
    private Label clientSurname;
    @FXML
    private Button toLoans_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.clientIdLabel.setText(resourceBundle.getString("lblClientID"));
        this.clientName.setText(resourceBundle.getString("lblname"));
        this.clientSurname.setText(resourceBundle.getString("lblsurname"));
        this.toLoans_btn.setText(resourceBundle.getString("lblLoans"));
    }
}
