package com.example.knk_gr23.Controllers.Admin;

import com.example.knk_gr23.App.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuControllers implements Initializable {
    @FXML
    private Button create_client_btn;
    @FXML
    private Button clients_btn;
    @FXML
    private Button requests_btn;
    @FXML
    private Button logout_btn;

    @FXML
    private Text secure_credit;




    @FXML
    public void handleLogOut(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.create_client_btn.setText(resourceBundle.getString("lblCreateClient"));
        this.clients_btn.setText(resourceBundle.getString("lblClients"));
        this.requests_btn.setText(resourceBundle.getString("lblRequests"));
        this.logout_btn.setText(resourceBundle.getString("lblLogout"));
        this.secure_credit.setText(resourceBundle.getString("lblSecureCredit"));
    }

    public void handleCreateClient(ActionEvent actionEvent) {
       // Navigator.navigate(actionEvent, Navigator.CREATE_CLIENT_PAGE);
    }

    public void handleClients(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent, Navigator.ADMIN_PAGE);
    }

    public void handleRequests(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent, Navigator.REQUESTS_PAGE);
    }
}
