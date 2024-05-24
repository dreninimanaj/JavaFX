package com.example.knk_gr23.Controllers.Client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PayDebtController implements Initializable {
    @FXML
    private Label creditCard;
    @FXML
    private Label type_pin;
    @FXML
    private Button button_confirm;
    @FXML
    private Text payDebt;
    @FXML
    private Button button_exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.creditCard.setText(resourceBundle.getString("lblCrediCard"));
        this.type_pin.setText(resourceBundle.getString("lblTypeYourPin"));
        this.button_confirm.setText(resourceBundle.getString("lblConfirm"));
        this.payDebt.setText(resourceBundle.getString("lblPayDebt"));
        this.button_exit.setText(resourceBundle.getString("lblExit"));
    }
}
