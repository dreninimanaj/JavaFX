package com.example.knk_gr23.Controllers.Admin;

import com.example.knk_gr23.App.SessionMenager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileAdminController implements Initializable {
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
    @FXML
    private Label emri_lbl;
    @FXML
    private Label mbiemri_lbl;
    @FXML
    private Label telefoni_lbl;
    @FXML
    private Label emaili_lbl;
    @FXML
    private Label punsimi_lbl;
    @FXML
    private Label adresa_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.name.setText(resourceBundle.getString("lblName"));
        this.last_name.setText(resourceBundle.getString("lblLastName"));
        this.iphone.setText(resourceBundle.getString("lblPhone"));
        this.email.setText(resourceBundle.getString("lblEmail"));
        this.address.setText(resourceBundle.getString("lblAddress"));
        this.employment_status.setText(resourceBundle.getString("lblEmploymentStatus"));

        emri_lbl.setText(SessionMenager.getClient().getName());
        emaili_lbl.setText(SessionMenager.getClient().getEmail());
        telefoni_lbl.setText(SessionMenager.getClient().getPhone());
        adresa_lbl.setText(SessionMenager.getClient().getAddress());
        punsimi_lbl.setText(SessionMenager.getClient().getEmploymentStatus());

    }

}