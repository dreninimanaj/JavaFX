package com.example.knk_gr23.Controllers.Client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientProfileController implements Initializable {
    @FXML
    private Text name;
    @FXML
    private Text last_name;
    @FXML
    private Text iphone ;
    @FXML
    private Text email;
    @FXML
    private Text address;
    @FXML
    private Text employment_status;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.name.setText(resourceBundle.getString("lblName"));
        this.last_name.setText(resourceBundle.getString("lblLastName"));
        this.iphone.setText(resourceBundle.getString("lblPhone"));
        this.email.setText(resourceBundle.getString("lblEmail"));
        this.address.setText(resourceBundle.getString("lblAddress"));
        this.employment_status.setText(resourceBundle.getString("lblEmploymentStatus"));



    }

}
