package com.example.knk_gr23.Controllers.Client;

import com.example.knk_gr23.App.Navigator;
import com.example.knk_gr23.App.SessionMenager;
import com.example.knk_gr23.Services.LoanService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PayDebtController implements Initializable {
    @FXML
    private Button button_exit;
    @FXML
    private Button button_confirm;
    @FXML
    private Label type_pin;
    @FXML
    private Label creditCard;
    @FXML
    private Text payDebt;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.creditCard.setText(resourceBundle.getString("lblCreditCard"));
        this.type_pin.setText(resourceBundle.getString("lblTypeyourpin"));
        this.button_confirm.setText(resourceBundle.getString("lblConfirm"));
        this.button_exit.setText(resourceBundle.getString("lblExit"));
    }

    public void handleConfirm(ActionEvent actionEvent) {
        SessionMenager.getLoan().getLoan_id();
        LoanService.removeTop(SessionMenager.getLoan().getLoan_id());
        Navigator.navigate(actionEvent, Navigator.TABLE_PAGE);
    }

    public void handleExit(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent, Navigator.HOME_PAGE);
    }
}
