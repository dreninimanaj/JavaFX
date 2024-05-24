package com.example.knk_gr23.Controllers.Client;

import com.example.knk_gr23.App.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;



import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {

    @FXML
    private Button home_btn;
    @FXML
    private Button apply_btn;
    @FXML
    private Button profile_btn;
    @FXML
    private Button logout_btn;
    @FXML
    private Button add_btn;
    @FXML
    private Text safe_Credits;
    @FXML
    private Text apply_loan;
    @FXML
    private Label write_data;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.home_btn.setText(resourceBundle.getString("lblHome"));
        this.apply_btn.setText(resourceBundle.getString("lblApply"));
        this.profile_btn.setText(resourceBundle.getString("lblProfile"));
        this.add_btn.setText(resourceBundle.getString("lblAdd"));
        this.safe_Credits.setText(resourceBundle.getString("lblSafeCredits"));
        this.apply_loan.setText(resourceBundle.getString("lblApplyForALoan"));
        this.write_data.setText(resourceBundle.getString("lblApplyForALoan"));
        this.logout_btn.setText(resourceBundle.getString("lblLogout"));

    }

    @FXML
    public void handleLogout(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @FXML
    private void handleApply(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CALCULATOR_PAGE);
    }

    public void handleProfile(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent, Navigator.CLIENT_PROFILE_PAGE);
    }

    @FXML
    public void handleHome(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }


}
